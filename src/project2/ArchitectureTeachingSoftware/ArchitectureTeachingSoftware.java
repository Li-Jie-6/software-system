package project2.ArchitectureTeachingSoftware;

import project2.demo1.demo1;
import project2.demo2.demo2;
import project2.demo3.demo3;
import project2.demo4.demo4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class ArchitectureTeachingSoftware {

    private JFrame frame;
    private JFileChooser fileChooser;
    private JTextArea textArea;
    private JComboBox<String> architectureComboBox;
    private JLabel statusLabel;
    private String currentArchitectureStyle;
    private JTabbedPane infoTabbedPane;

    public ArchitectureTeachingSoftware() {
        frame = new JFrame("经典软件体系结构教学软件");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        fileChooser = new JFileChooser();

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel topPanel = new JPanel(new FlowLayout());
        JButton openFileButton = new JButton("打开文件");
        openFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });
        topPanel.add(openFileButton);

        architectureComboBox = new JComboBox<>(new String[]{"请选择","主-子 程序", "面向对象", "事件系统", "管道-过滤器"});
        architectureComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox combo = (JComboBox) e.getSource();
                currentArchitectureStyle = (String) combo.getSelectedItem();
                statusLabel.setText("已选择体系结构风格: " + currentArchitectureStyle);
                updateInfoTabbedPane();
            }
        });
        topPanel.add(architectureComboBox);

        statusLabel = new JLabel("请选择体系结构风格并打开文件");
        topPanel.add(statusLabel);

        frame.add(topPanel, BorderLayout.NORTH);

        JButton processButton = new JButton("处理文件");
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    processFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        frame.add(processButton, BorderLayout.SOUTH);

        infoTabbedPane = new JTabbedPane();
        frame.add(infoTabbedPane, BorderLayout.EAST);
    }

    private void openFile() {
        int returnValue = fileChooser.showOpenDialog(frame);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                StringBuilder content = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                reader.close();
                textArea.setText(content.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "无法读取文件: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void processFile() throws IOException {
        if (textArea.getText().isEmpty() || currentArchitectureStyle == null) {
            JOptionPane.showMessageDialog(frame, "请先选择一个体系结构风格并打开文件", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // 在这里添加处理文件的逻辑
        JOptionPane.showMessageDialog(frame, "使用 " + currentArchitectureStyle + " 风格处理文件", "信息", JOptionPane.INFORMATION_MESSAGE);
        switch (currentArchitectureStyle){
            case "主-子 程序":
                demo1 demo1 = new demo1();
                demo1.test(fileChooser.getSelectedFile().getAbsolutePath());break;
            case "面向对象":
                demo2 demo2 = new demo2();
                demo2.test(fileChooser.getSelectedFile().getAbsolutePath());break;
            case "事件系统":
                demo3 demo3 = new demo3();
                demo3.test(fileChooser.getSelectedFile().getAbsolutePath());break;
            case "管道-过滤器":
                demo4 demo4 = new demo4();
                demo4.test(fileChooser.getSelectedFile().getAbsolutePath());break;
            default:break;
        }
        output();
    }

    private void output(){
        File file=new File("src/project2/resouces/output.txt");
        if(file.exists()){
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                StringBuilder content = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                reader.close();
                textArea.setText(content.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "无法读取文件: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateInfoTabbedPane() {
        infoTabbedPane.removeAll();
        if (currentArchitectureStyle != null&&currentArchitectureStyle.equals("请选择")==false) {
            //添加源代码结构的选项卡
            JPanel codePanel = new JPanel(new BorderLayout());
            codePanel.add(new JLabel("源程序代码结构"), BorderLayout.NORTH);
            // 添加原理图图片的选项卡
            JPanel imagePanel = new JPanel(new BorderLayout());
            imagePanel.add(new JLabel("原理图"), BorderLayout.NORTH);

            switch (currentArchitectureStyle){
                case "主-子 程序":
                    //添加原理图
                    addImage("src/project2/resouces/img.png",codePanel);
                    addImage("src/project2/resouces/demo1.png",imagePanel);
                    break;
                case "面向对象":
                    addImage("src/project2/resouces/img_1.png",codePanel);
                    addImage("src/project2/resouces/demo2.png",imagePanel);
                    break;
                case "事件系统":
                    addImage("src/project2/resouces/img_2.png",codePanel);
                    addImage("src/project2/resouces/demo3.png",imagePanel);
                    break;
                case "管道-过滤器":
                    addImage("src/project2/resouces/img_3.png",codePanel);
                    addImage("src/project2/resouces/demo4.png",imagePanel);
                    break;
                default:break;
            }
            infoTabbedPane.addTab(currentArchitectureStyle + " 源代码结构", codePanel);
            infoTabbedPane.addTab(currentArchitectureStyle + " 原理图", imagePanel);
        }
    }

    private void addImage(String imagePath,JPanel imagePanel){
        ImageIcon imageIcon = new ImageIcon(imagePath);
        JLabel imageLabel = new JLabel(imageIcon);
        // 调整图片大小或布局以适应界面
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imagePanel.add(imageLabel, BorderLayout.CENTER);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ArchitectureTeachingSoftware().frame.setVisible(true);
            }
        });
    }
}