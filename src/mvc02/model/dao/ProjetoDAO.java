/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc02.model.dao;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import mvc02.model.vo.Projeto;

/**
 *
 * @author wcsan_000
 */
public class ProjetoDAO {

    public ProjetoDAO() {
//        File arquivoProjeto = new File("projeto.xml");
//        XStream xmlStream = new XStream(new DomDriver());
//        if (!arquivoProjeto.exists()) {
//            FileOutputStream streamGravar;
//            try {
//                streamGravar = new FileOutputStream(arquivoProjeto);
//                streamGravar.write("<?xml version=\"1.0\"?>".getBytes("UTF-8"));
//                streamGravar.close();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
    }
    

    public Projeto getFromArquivo(File arquivoProjeto) {
//        File arquivoProjeto = new File("projeto.xml");
        XStream xmlStream = new XStream(new DomDriver());
        xmlStream.omitField(Projeto.class, "projetoListeners");

        if (arquivoProjeto.exists()) {
            FileInputStream streamLer;
            try {
                streamLer = new FileInputStream(arquivoProjeto);
                Projeto projetoAux = (Projeto) xmlStream.fromXML(streamLer);
                return projetoAux;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public void saveToArquivo(Projeto projeto, File arquivoProjeto) {
        XStream stream = new XStream(new DomDriver());
        stream.omitField(Projeto.class, "projetoListeners");
        FileOutputStream streamGravar;
        try {
            String xml = stream.toXML(projeto);
            streamGravar = new FileOutputStream(arquivoProjeto);
            streamGravar.write("<?xml version=\"1.0\"?>".getBytes("UTF-8"));
            byte[] bytes = xml.getBytes("UTF-8");
            streamGravar.write(bytes);
            streamGravar.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
}
