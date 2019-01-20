/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc02.view;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author wcsan_000
 */
public class ParamView {

    private double painelPrincipalHeight;
    private double painelPrincipalWidth;
    private double painelSplitDivider;
    private String lastFolder;

    public ParamView() {
        File arquivoParam = new File("paramView.xml");
        XStream xmlStream = new XStream(new DomDriver());
        if (!arquivoParam.exists()) {
            this.painelPrincipalHeight = 600.0;
            this.painelPrincipalWidth = 900.0;
            this.painelSplitDivider = 200;
            FileOutputStream streamGravar;
            try {
                String xml = xmlStream.toXML(this);
                streamGravar = new FileOutputStream(arquivoParam);
                streamGravar.write("<?xml version=\"1.0\"?>".getBytes("UTF-8"));
                byte[] bytes = xml.getBytes("UTF-8");
                streamGravar.write(bytes);
                streamGravar.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public ParamView getFromArquivo() {
        File arquivoParam = new File("paramView.xml");
        XStream xmlStream = new XStream(new DomDriver());
        if (arquivoParam.exists()) {
            FileInputStream streamLer;
            try {
                streamLer = new FileInputStream(arquivoParam);
                ParamView paramViewAux = (ParamView) xmlStream.fromXML(streamLer);
                return paramViewAux;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public void saveToArquivo() {
        XStream stream = new XStream(new DomDriver());
        File arquivoParam = new File("paramView.xml");
        FileOutputStream streamGravar;
        try {
            String xml = stream.toXML(this);
            streamGravar = new FileOutputStream(arquivoParam);
            streamGravar.write("<?xml version=\"1.0\"?>".getBytes("UTF-8"));
            byte[] bytes = xml.getBytes("UTF-8");
            streamGravar.write(bytes);
            streamGravar.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public double getPainelPrincipalHeight() {
        return painelPrincipalHeight;
    }

    public void setPainelPrincipalHeight(double painelPrincipalHeight) {
        this.painelPrincipalHeight = painelPrincipalHeight;
    }

    public double getPainelPrincipalWidth() {
        return painelPrincipalWidth;
    }

    public void setPainelPrincipalWidth(double painelPrincipalWidth) {
        this.painelPrincipalWidth = painelPrincipalWidth;
    }

    public double getPainelSplitDivider() {
        return painelSplitDivider;
    }

    public void setPainelSplitDivider(double painelSplitDivider) {
        this.painelSplitDivider = painelSplitDivider;
    }

    public String getLastFolder() {
        if (lastFolder == null){
            return "c:/";
        }
        return lastFolder;
    }

    public void setLastFolder(String lastFolder) {
        this.lastFolder = lastFolder;
    }
}
