/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.fenix.bean;

import com.wrm.draftstore.common.entidades.CarrinhoVenda;
import com.wrm.draftstore.common.entidades.Endereco;
import com.wrm.draftstore.common.entidades.TipoPagamento;
import com.wrm.draftstore.common.service.CarrinhoVendaService;
import com.wrm.draftstore.common.service.jpaimpl.CarrinhoVendaServiceJPAImpl;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author ramonhonorio
 */
@ManagedBean
@SessionScoped
public class VendaBean implements Serializable {

    public Endereco enderecoSelecionado;
    public TipoPagamento tp = new TipoPagamento(2);
    public CarrinhoVenda cv;
    
    private final CarrinhoVendaService cvs = new CarrinhoVendaServiceJPAImpl();
    
    private static String draftmail = "wrm.draftstore@gmail.com";
    private static String draftpw = "DR@FT123";

    public Endereco getEnderecoSelecionado() {
        return enderecoSelecionado;
    }

    public void setEnderecoSelecionado(Endereco enderecoSelecionado) {
        this.enderecoSelecionado = enderecoSelecionado;
    }

    public CarrinhoVenda getCv() {
        return cv;
    }

    public void setCv(CarrinhoVenda cv) {
        this.cv = cv;
    }

    public VendaBean() {
        this.enderecoSelecionado = new Endereco();
        this.cv = new CarrinhoVenda();
        this.cv.setDataVenda(new Date());
        this.cv.setFrete(0);
        this.cv.setPrazoEntrega(10);
        this.cv.setFkTipoPagamento(tp);
    }

    public TipoPagamento getTp() {
        return tp;
    }

    public void setTp(TipoPagamento tp) {
        this.tp = tp;
    }

    public VendaBean(Endereco enderecoSelecionado, CarrinhoVenda cv) {
        this.enderecoSelecionado = enderecoSelecionado;
        this.cv = cv;
    }

    public String selecionarEndereco(Endereco e) {
        enderecoSelecionado = e;
        this.cv.setFkEndereco(e);
        return "compra_confirmarEndereco.xhtml?faces-redirect=true";
    }

    public CarrinhoVenda realizarVenda() {
        CarrinhoVenda newCV = cvs.incluir(this.cv);

//        if (newCV != null) {
//            // Venda bem sucedida
//            enviarEmail("raamonz@hotmail.com", newCV.getIdVenda());
//        }

        return newCV;
    }

//    public void enviarEmail(String destinatario) {
//        // Recipient's email ID needs to be mentioned.
//        String to = destinatario;
//
//        // Get system properties
//        Properties properties = System.getProperties();
//        properties.setProperty("mail.user", "ramonaqh@gmail.com");
//        properties.setProperty("mail.password", "#D010421@#$");
//
//    }

    public void enviarEmail(String destinatario, Integer idVenda) {
        Properties props = new Properties();
        /**
         * Parâmetros de conexão com servidor Gmail
         */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
            new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("wrm.draftstore@gmail.com", "DR@FT123");
                }
            });

        /**
         * Ativa Debug para sessão
         */
        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("wrm.draftstore@gmail.com", "DraftStore e-Commerce")); //Remetente

            Address[] toUser = InternetAddress //Destinatário(s)
                    .parse(destinatario);

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("Compra realizada com sucesso! #000"+idVenda);//Assunto
            message.setText(
                    "Olá, gostaríamos de lhe informar que sua compra #000"+idVenda+" foi efetuada com sucesso. \n"
                    + "Aguardamos agora o pagamento do boleto bancário.");
            /**
             * Método para enviar a mensagem criada
             */
            Transport.send(message);

            System.out.println("Email para "+destinatario+" foi enviado com sucesso. #000"+idVenda);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(VendaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
