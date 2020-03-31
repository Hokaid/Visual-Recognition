import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.visual_recognition.v3.model.ClassifyOptions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualRecognition {
    private JPanel panel1;
    private JTextField linkim;
    private JButton sendb;
    private JLabel label;
    private JLabel resul;

    public VisualRecognition() {
        sendb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                IamAuthenticator authenticator = new IamAuthenticator("lJWDSwvqfTOaSinqh52F62XjPl6k20Xcr8UDtp_77W6L");
                ClassifyOptions classifyOptions = new ClassifyOptions.Builder().url(linkim.getText()).build();
                com.ibm.watson.visual_recognition.v3.VisualRecognition visualRecognition = new com.ibm.watson.visual_recognition.v3.VisualRecognition("2018-03-19", authenticator);
                visualRecognition.setServiceUrl("https://api.us-south.visual-recognition.watson.cloud.ibm.com/instances/d632f43e-5125-4273-915f-3513ee7a543f");
                ClassifiedImages resultado = visualRecognition.classify(classifyOptions).execute().getResult();
                int size = resultado.getImages().get(0).getClassifiers().get(0).getClasses().size();
                for (int i=0; i < resultado.getImages().get(0).getClassifiers().get(0).getClasses().size(); i++) {
                    if (i == size-1){
                        resul.setText(resul.getText() + " " + resultado.getImages().get(0).getClassifiers().get(0).getClasses().get(i).getXClass());
                    }
                    else if (i == size-2){
                        resul.setText(resul.getText() + " " + resultado.getImages().get(0).getClassifiers().get(0).getClasses().get(i).getXClass() + " and");
                    }
                    else if (i < size-2){
                        resul.setText(resul.getText() + " " + resultado.getImages().get(0).getClassifiers().get(0).getClasses().get(i).getXClass() + ",");
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("VisualRecognition");
        frame.setContentPane(new VisualRecognition().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
