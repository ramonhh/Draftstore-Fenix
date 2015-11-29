/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.fenix.bean;

import com.wrm.draftstore.common.entidades.Usuario;
import com.wrm.draftstore.common.service.UsuarioService;
import com.wrm.draftstore.common.service.fakeimpl.UsuarioServiceFakeImpl;
import com.wrm.draftstore.common.service.jpaimpl.UsuarioServiceJPAImpl;
import com.wrm.draftstore.fenix.entity.UsuarioSistema;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ramonhonorio
 */
@Named(value = "usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {

    // Construtor padrao
    public UsuarioBean() {
    }

    // Usuarios Fake
//  private UsuarioService usuarioService = new UsuarioServiceFakeImpl();
    // Usuarios DB
    private UsuarioService usuarioService = new UsuarioServiceJPAImpl();

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private String nome;
    private String email;
    private String senha;

    private Usuario usuarioLogado;
    private UsuarioSistema usuarioSistema;

    // Novo usuario
    private Usuario novoUsuario;

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Metodos">
    public String efetuarLogin(int qttProdutoCarrinho) {
        UsuarioSistema usuarioSis = autenticar(email, senha);
        if (usuarioSis != null) {
            this.usuarioSistema = usuarioSis;
            System.out.println("[WRMLOG] Login efetuado com sucesso"
                    + "\n> " + usuarioLogado.getNome() + " logado.");

            return qttProdutoCarrinho > 0 ? "/carrinho.xhtml?faces-redirect=true" : "/perfil.xhtml?faces-redirect=true";
        }
        return "/home.xhtml?faces-redirect=true";
    }

    public String efetuarLogout() {
        usuarioLogado = null;
        return "/home.xhtml?faces-redirect=true";
    }

    public UsuarioSistema autenticar(String email, String senha) {
        Usuario usuario = usuarioService.realizarLogin(email, senha);
        if (usuario != null) {
            UsuarioSistema userSis = new UsuarioSistema(email, senha, new String[]{"ADMIN"});
            this.usuarioLogado = usuario;
            return userSis;
        }
        return null;
    }

    public void inicializarUsuario() {
        System.out.println("Inicializando usuario...");
        novoUsuario = new Usuario();
//        novoUsuario.setDtNascimento(new Date());
    }

    public String cadastrarUsuario() {
        System.out.println("Cadastrando...");

        this.carregarParametrosRequestUsuario();

        if (novoUsuario != null) {
            novoUsuario.setDataCriacao(new Date());
            usuarioService.incluir(novoUsuario);
            System.out.println("Usuário cadastrado.\nLogando...");
            this.email = novoUsuario.getEmail();
            this.senha = novoUsuario.getSenha();
            return this.efetuarLogin(0);
        } else {
            System.out.println("Usuário não cadastrado.");
            return "cadastro.xhtml?faces-redirect=true";
        }
    }

    public void carregarParametrosRequestUsuario() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String nomeRequest = request.getParameter("formCadastro:nome");
        String sexo = request.getParameter("formCadastro:sSexo");
        String dtNascimento = request.getParameter("formCadastro:dtNascimento");
        String cpf = request.getParameter("formCadastro:cpf");
        String telefone = request.getParameter("formCadastro:telefone");
        String emailRequest = request.getParameter("formCadastro:email");
        String senhaRequest = request.getParameter("formCadastro:senha");

        novoUsuario.setNome(nomeRequest);
        novoUsuario.setSexo(sexo.toCharArray()[0]);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedCurrentDate = new Date();
        try {
            convertedCurrentDate = sdf.parse(dtNascimento);
        } catch (ParseException ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        novoUsuario.setDtNascimento(convertedCurrentDate);

        novoUsuario.setCpf(cpf);
        novoUsuario.setTelefone(telefone);
        novoUsuario.setCelular(telefone);
        novoUsuario.setEmail(emailRequest);
        novoUsuario.setSenha(senhaRequest);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters e setters">
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public UsuarioSistema getUsuarioSistema() {
        return usuarioSistema;
    }

    public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
    }

    public Usuario getNovoUsuario() {
        return novoUsuario;
    }

    public void setNovoUsuario(Usuario novoUsuario) {
        this.novoUsuario = novoUsuario;
    }
      //</editor-fold>
}
