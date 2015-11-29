/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.fenix.bean;

import com.wrm.draftstore.common.entidades.Carrinho;
import com.wrm.draftstore.common.entidades.CarrinhoVenda;
import com.wrm.draftstore.common.entidades.Produto;
import com.wrm.draftstore.common.entidades.Usuario;
import com.wrm.draftstore.common.service.CarrinhoService;
import com.wrm.draftstore.common.service.jpaimpl.CarrinhoServiceJPAImpl;
import com.wrm.draftstore.fenix.entity.ProdutoCarrinho;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ramonhonorio
 */
@Named(value = "carrinhoBean")
@SessionScoped
public class CarrinhoBean implements Serializable {

    /**
     * Creates a new instance of CarrinhoBean
     */
    public CarrinhoBean() {
        this.compras = new ArrayList<>();
    }

    private ArrayList<ProdutoCarrinho> itens = new ArrayList<>();
    private HashMap<Produto, ProdutoCarrinho> itensMap = new HashMap<>();
    private int quantidadeItensMap = 0;
    private final CarrinhoService cs = new CarrinhoServiceJPAImpl();
    private List<Carrinho> compras;

    public List<Carrinho> getCompras() {
        return compras;
    }

    public void setCompras(List<Carrinho> compras) {
        this.compras = compras;
    }

    public void carregarCompras(Usuario u) {
        this.compras = cs.listarDoUsuario(u);
    }

    public float getPrecoTotal() {
        float preco = calcularPrecoCarrinho();
        if (preco < 0) {
            return calcularPrecoCarrinho();
        }
        return preco;
    }

    public ArrayList<ProdutoCarrinho> getItens() {
        return itens;
    }

    public void adicionar(Produto produto) {
        System.out.println("Adicionando o produto " + produto.getModelo() + " ao carrinho...");
        ProdutoCarrinho pc = this.itensMap.get(produto);

        if (pc == null) {
            this.itensMap.put(produto, new ProdutoCarrinho(produto, new Date()));
            this.quantidadeItensMap++;
        } else {
            if (produto.getQuantidade() > pc.getQuantidade()) {
                pc.setQuantidade(pc.getQuantidade() + 1);

                this.quantidadeItensMap++;
            }
            this.animarCarrinho();
        }
    }

    public boolean removerProduto(Produto produto) {
        ProdutoCarrinho pc = this.itensMap.get(produto);

        if (pc != null) {
            System.out.println(produto.getModelo() + " foi removido com sucesso.");
            this.quantidadeItensMap -= pc.getQuantidade();
            this.itensMap.remove(produto);
            return true;
        }
        System.out.println("Erro ao tentar remover " + produto.getModelo());

        return false;
    }

    public void handleChange(ValueChangeEvent event) {
        if (Integer.parseInt(event.getNewValue().toString()) > 0) {
            this.quantidadeItensMap -= Integer.parseInt(event.getOldValue().toString());
            this.quantidadeItensMap += Integer.parseInt(event.getNewValue().toString());
        }
    }

    public int getQuantidadeProduto(Produto produto, int quantidade) {
        ProdutoCarrinho pc = this.itensMap.get(produto);
        return pc.getQuantidade();
    }

    public void limparCarrinho() {
        this.itensMap.clear();
        this.itensMap = new HashMap<>();
        this.quantidadeItensMap = 0;
        System.out.println("O carrinho foi limpo!");
    }

    public void checkout() {
        System.out.println("Bem, aqui temos que realizar a compra...");
    }

    public void animarCarrinho() {
        String script = "$('#labelCarrinho').change();";
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(script);
    }

    public void imprimirCarrinho() {
        System.out.println("Carrinho de compras:");
        for (Produto item : this.itensMap.keySet()) {
            System.out.println("ITEM: " + item.getModelo());
        }
        System.out.println("--------------------");
    }

    public String finalizarCompra(Usuario usuLogado, CarrinhoVenda cv) {
        for (ProdutoCarrinho itensMapValue : this.getItensMapValues()) {
            Carrinho car = new Carrinho();
            Produto p = itensMapValue.getProduto();
            car.setDataAlteracao(itensMapValue.getDataInclusao());
            car.setFkCarrinhoVenda(cv);
            car.setFkProduto(p);
            car.setFkUsuario(usuLogado);
            car.setPreco(p.isDataValida() ? p.getPrecoPromo() : p.getPrecoVenda());
            car.setQuantidade(itensMapValue.getQuantidade());

            cs.incluir(car);
        }

        limparCarrinho();
        
        enviarEmail(usuLogado.getEmail(), cv.getIdVenda());
        
        return "/compra_sucesso.xhtml?faces-redirect=true";
    }

    public float calcularPrecoCarrinho() {
        float valor = 0;

        for (ProdutoCarrinho pc : this.getItensMapValues()) {
            if (pc.getQuantidade() < 1) {
                return -1;
            }

            valor += pc.getProduto().getPrecoVenda().floatValue() * pc.getQuantidade();
        }

        return valor;
    }

    public HashMap<Produto, ProdutoCarrinho> getItensMap() {
        return itensMap;
    }

    public void setItensMap(HashMap<Produto, ProdutoCarrinho> mapaItens) {
        this.itensMap = mapaItens;
        this.quantidadeItensMap = this.itensMapRecount(mapaItens);
    }

    public List<Produto> getItensMapKeys() {
        return new ArrayList<>(this.itensMap.keySet());
    }

    public List<ProdutoCarrinho> getItensMapValues() {
        return new ArrayList<>(this.itensMap.values());
    }

    public int getQuantidadeItensMap() {
        return quantidadeItensMap;
    }

    public void setQuantidadeItensMap(int quantidadeItensMap) {
        this.quantidadeItensMap = quantidadeItensMap;
        this.animarCarrinho();
    }

    private ProdutoCarrinho getProdutoCarrinhoFromMap(Map<Produto, ProdutoCarrinho> hm, ProdutoCarrinho value) {
        for (Produto o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return hm.get(o);
            }
        }
        return null;
    }

    public int itensMapRecount(HashMap<Produto, ProdutoCarrinho> mapaItens) {
        int soma = 0;
        for (ProdutoCarrinho pc : mapaItens.values()) {
            soma += pc.getQuantidade();
        }
        return soma;
    }

    public String prosseguirCompra(Usuario u) {

        if (u == null) {
            System.out.println("Não está logado");

            return "login.xhtml";
        }

        return "compra_endereco.xhtml";
    }

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
