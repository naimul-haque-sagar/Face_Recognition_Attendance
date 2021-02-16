
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
import db_connection.Employee_Attendance_Operation;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class EmployeeAttendance extends javax.swing.JFrame {
    private EmployeeAttendance.DaemonThread myThread = null;
    VideoCapture webSource = null;
    Mat cameraImage = new Mat();
    CascadeClassifier cascade = new CascadeClassifier( Properties.basePath + "Computer_vision_attendance/haarcascade_frontalface_alt.xml");
    BytePointer mem = new BytePointer();
    FaceRecognizer recognizer=LBPHFaceRecognizer.create();
    RectVector detectedFaces = new RectVector();
    String root ,firstNamePerson,lastNamePerson,officePerson,dobPerson;
    int idPerson;
    Employee_Attendance_Operation employeeAttendanceOperation = new Employee_Attendance_Operation();
    DB_Connection cd = new DB_Connection();

    public EmployeeAttendance() {
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
        label_photo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Face Recognizer");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_photo.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel1.add(label_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 430, 280));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Stop camera");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 430, 100));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 470, 490));

        setSize(new java.awt.Dimension(499, 540));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        stopCamera();
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeAttendance().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
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
//                                    label_name.setText("Not Recognized");
//                                    labelOffice.setText("Not Found");
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
                        String sql="SELECT * FROM employee WHERE id ="+String.valueOf(idPerson);
                        cd.executesql(sql);
                        while(cd.resultSet.next()){
                            System.out.println(cd.resultSet.getString("first_name")+" "+ cd.resultSet.getString("last_name"));
                            System.out.println("employee : "+cd.resultSet.getString("id"));
                            employeeAttendanceOperation.storeEmployeeAttendance(idPerson);
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
        myThread=new EmployeeAttendance.DaemonThread();
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
