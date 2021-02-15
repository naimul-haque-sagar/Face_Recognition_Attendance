package register;

import config.Properties;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.IntBuffer;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.bytedeco.javacpp.BytePointer;
import static org.bytedeco.opencv.global.opencv_core.CV_32SC1;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imencode;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
import org.bytedeco.opencv.global.opencv_imgproc;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_face.FaceRecognizer;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;
import db_connection.DB_Connection;
import dao.StoreStudentInformation;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Student;

public class CaptureFace extends javax.swing.JFrame {

    String harcascade= Properties.basePath + "Computer_vision_attendance/haarcascade_frontalface_alt.xml";
    String root ,first_name,last_name,student_section;
    int numSamples = 5, sample = 1,studentId,employeeId,student_class;
    DB_Connection cd = new DB_Connection();
    
    private CaptureFace.DaemonThread myThread = null;
    VideoCapture webSource = null;
    Mat cameraImage = new Mat();
    CascadeClassifier cascade = new CascadeClassifier(harcascade);
    BytePointer mem = new BytePointer();
    RectVector detectedFaces = new RectVector();

    public CaptureFace(int id,String first_name,String last_name,int student_class,String student_section){
        initComponents();
        this.studentId=id;
        this.first_name=first_name;
        this.last_name=last_name;
        this.student_class= student_class;
        this.student_section= student_section;
        startCamera();
    }
    
    public CaptureFace(int id,String first_name,String last_name){
        initComponents();
        this.employeeId=id;
        this.first_name=first_name;
        this.last_name=last_name;
        startCamera();
    }

    private CaptureFace() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        label_photo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        counterLabel = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Face capture");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Capture 25 snapshot");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 420, 30));

        label_photo.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(200, 200, 200)));
        jPanel1.add(label_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 420, 280));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        counterLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        counterLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        counterLabel.setText("00");
        jPanel2.add(counterLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 380, 30));

        saveButton.setText("Capture");
        jPanel2.add(saveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 420, 100));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 470, 470));

        setSize(new java.awt.Dimension(507, 531));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CaptureFace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CaptureFace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CaptureFace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CaptureFace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CaptureFace().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel counterLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel label_photo;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables

    class DaemonThread implements Runnable {

        protected volatile boolean runnable = false;

        @Override
        public void run(){
            synchronized (this) {
                while (runnable) {
                    try {
                        if (webSource.grab()) {
                            webSource.retrieve(cameraImage);
                            Graphics g = label_photo.getGraphics();
                            Mat imageColor = new Mat();
                            imageColor = cameraImage;

                            Mat imageGray = new Mat();
                            cvtColor(imageColor, imageGray, COLOR_BGRA2GRAY);

                            RectVector detectedFaces = new RectVector();
                            cascade.detectMultiScale(imageColor, detectedFaces, 1.1, 1, 0, new Size(150, 150), new Size(500, 500));

                            for (int i = 0; i < detectedFaces.size(); i++) {
                                
                                Rect dadosFace = detectedFaces.get(0);
                                rectangle(imageColor, dadosFace, new Scalar(255, 255, 255, 5));
                                Mat face = new Mat(imageGray, dadosFace);
                                opencv_imgproc.resize(face, face, new Size(160, 160));

                                if (saveButton.getModel().isPressed()) {

                                    if (sample <= numSamples) {
                                        String cropped = Properties.basePath + "Computer_vision_attendance/Images/student." +studentId +"." + sample + ".jpg";
                                        imwrite(cropped, face);
                                        counterLabel.setText(String.valueOf(sample));
                                        sample++;
                                    }
                                    if (sample > 5) {
                                        generate();
                                        try {
                                            insertDatabase();
                                        } catch (SQLException ex) {
                                            Logger.getLogger(CaptureFace.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        System.out.println("person register done");
                                        stopCamera();
                                    }
                                    //cameraImage.close();
                                }
                            }

                            imencode(".bmp", cameraImage, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.getStringBytes()));
                            BufferedImage buff = (BufferedImage) im;

                            if (g.drawImage(buff, 0, 0, getWidth(), getHeight() - 90, 0, 0, buff.getWidth(), buff.getHeight(), null)) {
                                if (runnable == false) {
                                    System.out.println("Salve a Foto");
                                    this.wait();
                                }
                            }
                        }

                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error ao iniciar camera (IOEx)\n" + ex);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error ao iniciar camera (Interrupted)\n" + ex);
                    }
                }
            }
        }

    }

    public void generate() {
        File directory=new File(Properties.basePath + "Computer_vision_attendance/Images/");
        FilenameFilter filter=new FilenameFilter(){

            @Override
            public boolean accept(File dir, String name) {
               return name.endsWith(".jpg") || name.endsWith(".png");
            }
        
        };
        File[] files=directory.listFiles(filter);
        MatVector photos=new MatVector(files.length);
        Mat labels=new Mat(files.length,1,CV_32SC1);
        IntBuffer labelsBuffer=labels.createBuffer();

        int counter=0;
        for(File image: files){
            Mat photo=imread(image.getAbsolutePath(),CV_32SC1);
            int idPerson=Integer.parseInt(image.getName().split("\\.")[1]);
            opencv_imgproc.resize(photo, photo, new Size(160,160));
            
            photos.put(counter,photo);
            labelsBuffer.put(counter,idPerson);
            counter++;
        }
    
        FaceRecognizer lbph=LBPHFaceRecognizer.create();
        lbph.train(photos, labels);

        lbph.save(Properties.basePath + "Computer_vision_attendance/Images/classifierLBPH.yml");
    }

    public void insertDatabase() throws SQLException{
        StoreStudentInformation storeStudentInformation = new StoreStudentInformation();
        Student  student = new Student();
        student.setId(studentId);
        student.setFirst_name(first_name);
        student.setLast_name(last_name);
        student.setStudent_class(student_class);
        student.setClass_section(student_section);
        storeStudentInformation.insertStudentInformation(student);
    }

    public void stopCamera() {
        myThread.runnable = false;
        webSource.release();
        dispose();
    }

    public void startCamera() {
        webSource=new VideoCapture(0);
        myThread=new CaptureFace.DaemonThread();
        Thread t=new Thread(myThread);
        t.setDaemon(true);
        myThread.runnable=true;
        t.start();
    }
}
