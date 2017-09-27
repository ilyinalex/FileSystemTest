package GUI;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

/**
 * Created by ailyin on 18.09.17.
 */
public class MainFrame extends JFrame implements MouseListener{
    private JList jList;
    private File file;
    private File [] files;
    private String path;
    private String [] pathes;
    public MainFrame(){
        super();
        setSize(400,300);
        setLocation(100,100);
        add(jList = new JList());
        file = new File("/");
        add(new JScrollPane(jList));
        jList.addMouseListener(this);
        pathes = new String[65536];
        files = new File[65536];
        act("");
        setVisible(true);

    }
    private void act(String fileName){
        if(fileName == ""){
            path=file.getPath();
            file=new File(file.getPath()+"/"+fileName);
        }
        else
            file = new File("/" + fileName);
        files = file.listFiles();
        pathes = new String[65536];
        pathes[0] = "**";
        for(int i = 1;i < files.length; i++){
            if(files[i-1].isDirectory()) pathes[i] = "/" + files[i - 1].getName(); else
                pathes[i] = files[i - 1].getName();
        }
        jList.setListData(pathes);

    }

    private void path(){
        file = new File(file.getPath() + "/..");
        files = file.listFiles();
        pathes = new String[65536];
        pathes[0] = "**";
        for(int i = 1;i < files.length; i++){
            if(files[i-1].isDirectory()) pathes[i] = "/" + files[i - 1].getName(); else
                pathes[i] = files[i - 1].getName();
        }
        jList.setListData(pathes);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int selectedIndex = jList.getSelectedIndex();
        if(selectedIndex == 0){
            path();
        }
        else{
            if(selectedIndex < files.length && files[selectedIndex].isDirectory()){
                act(pathes[selectedIndex]);
            }
            else{
                try {
                    Runtime.getRuntime().exec("thunar");
                }
                catch (Exception e1){

                }
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
