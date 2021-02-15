
package attendance;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Array;
import javax.imageio.ImageIO;
import javax.swing.SwingWorker;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imencode;
import org.bytedeco.opencv.global.opencv_imgproc;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_face.FaceRecognizer;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;
import db_connection.DB_Connection;

import config.Properties;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class AttendanceCheck extends javax.swing.JFrame {
    private AttendanceCheck.DaemonThread myThread = null;
    VideoCapture webSource = null;
    Mat cameraImage = new Mat();
    CascadeClassifier cascade = new CascadeClassifier( Properties.basePath + "Computer_vision_attendance/haarcascade_frontalface_alt.xml");
    BytePointer mem = new BytePointer();
    FaceRecognizer recognizer=LBPHFaceRecognizer.create();
    RectVector detectedFaces = new RectVector();
    String root ,firstNamePerson,lastNamePerson,officePerson,dobPerson;
    int idPerson;
    DB_Connection cd = new DB_Connection();

    public AttendanceCheck() {
        initComponents();
        this.setResizable(false);
        recognizer.read(Properties.basePath + "Computer_vision_attendance/Images/classifierLBPH.yml");
        recognizer.setThreshold(80);
        startCamera();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        label_photo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        labelOffice = new javax.swing.JLabel();
        label_name = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Face Recognizer");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Capture 25 snapshot");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 420, 30));

        label_photo.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(200, 200, 200)));
        jPanel1.add(label_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 420, 280));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelOffice.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelOffice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelOffice.setText("Office");
        jPanel2.add(labelOffice, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 380, 30));

        label_name.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        label_name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_name.setText("Name");
        jPanel2.add(label_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 380, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 420, 100));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Stop camera");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 470, 520));

        setSize(new java.awt.Dimension(514, 577));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        stopCamera();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(AttendanceCheck.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AttendanceCheck.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AttendanceCheck.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AttendanceCheck.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AttendanceCheck().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelOffice;
    private javax.swing.JLabel label_name;
    private javax.swing.JLabel label_photo;
    // End of variables declaration//GEN-END:variables
class DaemonThread implements Runnable {

        protected volatile boolean runnable = false;

        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    try {
                        if (webSource.grab()) {
                            webSource.retrieve(cameraImage);
                            Graphics g = label_photo.getGraphics();

                            Mat imageGray = new Mat();
                            cvtColor(cameraImage, imageGray, COLOR_BGRA2GRAY);

                            RectVector detectedFace = new RectVector();
                            cascade.detectMultiScale(imageGray, detectedFace, 1.1, 2, 0, new Size(150, 150), new Size(500, 500));

                            for (int i = 0; i < detectedFace.size(); i++) {
                                Rect dadosFace = detectedFace.get(i);
                                rectangle(cameraImage, dadosFace, new Scalar(0, 255, 0, 0));
                                Mat faceCapturada = new Mat(imageGray, dadosFace);
                                opencv_imgproc.resize(faceCapturada, faceCapturada, new Size(160, 160));

                                IntPointer rotulo = new IntPointer(1);
                                DoublePointer confidence = new DoublePointer(1);
                                recognizer.predict(faceCapturada, rotulo, confidence);
                                int prediction = rotulo.get(0);
                                String name = null;
                                if (prediction == -1) {
                                    label_name.setText("Not Recognized");
                                    labelOffice.setText("Not Found");
                                    idPerson = 0;
                                } else {
                                    System.out.println(confidence.get(0));
                                    idPerson = prediction;
                                    rec();
                                }
                                //int x = Math.max(dadosFace.tl().x() - 10, 0);
                                //int y = Math.max(dadosFace.tl().y() - 10, 0);
                                //putText(imagemCamera, nome, new Point(x, y), FONT_HERSHEY_PLAIN, 1.7, new Scalar(0, 255, 0, 2));
                            }

                            imencode(".bmp", cameraImage, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.getStringBytes()));
                            BufferedImage buff = (BufferedImage) im;

                            if (g.drawImage(buff, 0, 0, getWidth(), getHeight() - 100, 0, 0, buff.getWidth(), buff.getHeight(), null)) {
                                if (runnable == false) {
                                    System.out.println("Saved a Picture");
                                    this.wait();
                                }
                            }
                        }
                    } catch (IOException | InterruptedException ex) {
                    }
                }
            }
        }

        private void rec() {
            SwingWorker worker=new SwingWorker() {

                @Override
                protected Object doInBackground() throws Exception {
                    cd.connectDatabase();
                    try {
                        String sql="SELECT * FROM student WHERE id ="+String.valueOf(idPerson);
                        cd.executesql(sql);
                        while(cd.resultSet.next()){
                            label_name.setText(cd.resultSet.getString("first_name")+" "+ cd.resultSet.getString("last_name"));
                            labelOffice.setText(cd.resultSet.getString("office"));
                            
                            System.out.println("person : "+cd.resultSet.getString("id"));
                            Array ident=cd.resultSet.getArray(2);
                            String[] person=(String[]) ident.getArray();
                            for (int i = 0; i < person.length; i++) {
                                System.out.println(person[i]);
                            }
                        }
                    } catch (Exception e) {
                    
                    }
                    cd.disconnectDatabase();
                    return null;   
                }
            };
            worker.execute();
        }

    }
      public void stopCamera() {
        myThread.runnable = false;
        webSource.release();
        
        if(isDispose) 
            dispose();
    }

    public void startCamera() {
        webSource=new VideoCapture(0);
        myThread=new AttendanceCheck.DaemonThread();
        Thread t=new Thread(myThread);
        t.setDaemon(true);
        myThread.runnable=true;
        t.start();
    }
    
    //TODO: on close action - call stopCamera
    @Override
    public synchronized void addWindowListener(WindowListener listener) {
        
        listener = new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                
                stopCamera();
            }
        };
    };

    @Override
    public void dispose() {
        isDispose = false; // otherwise cause an infinite loop 
        
        stopCamera();
        super.dispose();
    }
    
    private boolean isDispose = true;
}
