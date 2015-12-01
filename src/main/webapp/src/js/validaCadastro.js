/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var campoNome = document.getElementById("campoNome");
var campoDataNascimento = document.getElementById("campoDtNascimento");
var campoCpf = document.getElementById("campoCpf");
var campoContato = document.getElementById("campoContato");
var campoEmail = document.getElementById("campoEmail");
var campoSenha = document.getElementById("campoSenha");
var campoCsenha = document.getElementById("campoCsenha");

var nameNome = document.getElementsByClassName("formCastro:nome");
var nameCpf = document.getElementsByClassName("formCastro:cpf");
var nameContato = document.getElementsByClassName("formCastro:telefone");
var nameEmail = document.getElementsByClassName("formCastro:email");
var nameSenha = document.getElementsByClassName("formCastro:senha");
var nameCsenha = document.getElementsByClassName("formCastro:cSenha");

function validaNome() {
    var valorNome = document.getElementById("formCadastro:nome").value;
    if (valorNome === "") {
        campoNome.classList.add("error");
        return false;
    } else {
        campoNome.classList.remove("error");
        campoNome.classList.add("success");
        return true;
    }

}


function validaDataNascimento() {
    var valorData = new Date (document.getElementById("formCadastro:dtNascimento").value);
    if (!valorData.isValid()) {
        campoDataNascimento.classList.add("error");
        return false;
    } else {
        campoDataNascimento.classList.remove("error");
        campoDataNascimento.classList.add("success");
        return true;
    }

}

function validaCPF() {
    var reg = /^[0-9]{3}.?[0-9]{3}.?[0-9]{3}-?[0-9]{2}/;
    var valorDoCampo = $("#formCadastro\\:cpf").val();
    if (!reg.test(valorDoCampo) || !validarCPF(valorDoCampo)) {
        campoCpf.classList.add("error");
        return false;
    } else {
        campoCpf.classList.remove("error");
        campoCpf.classList.add("success");
        return true;
    }
}
function validaContato() {
    var valorDoCampo = $("#formCadastro\\:telefone").val();
    if (valorDoCampo.length < 14) {
        campoContato.classList.add("error");
        return false;
    } else {
        campoContato.classList.remove("error");
        campoContato.classList.add("success");
        return true;
    }

}
function validaEmail() {
    var regex = /^.+@.+\..{2,3}$/;
    var valorDoCampo = $("#formCadastro\\:email").val();
    if (!regex.test(valorDoCampo)) {
        campoEmail.classList.add("error");
        return false;
    } else {
        campoEmail.classList.remove("error");
        campoEmail.classList.add("success");
        return true;
    }
}
function validaSenha() {
    var senha = document.getElementById("formCadastro:senha").value;

    if (senha.length < 6) {
        campoSenha.classList.add("error");
        return false;
    } else {
        campoSenha.classList.remove("error");
        campoSenha.classList.add("success");
        return true;
    }
}
function validaCsenha() {
    var cSenha = document.getElementById("formCadastro:cSenha").value;
    var senha = document.getElementById("formCadastro:senha").value;

    if (senha !== cSenha) {
        campoCsenha.classList.add("error");
        return false;
    } else {
        campoCsenha.classList.remove("error");
        campoCsenha.classList.add("success");
        return true;
    }
}
function validaCadastro() {
    if (!validaNome()) {
        document.getElementsByClassName("formCadastro:nome").focus();
        return false;
    }
    if (!validaCPF()) {
        document.getElementsByClassName("formCadastro:cpf").focus();
        return false;
    }
    if (!validaContato()) {
        document.getElementsByClassName("formCadastro:telefone").focus();
        return false;
    }
    if (!validaEmail()) {
        document.getElementsByClassName("formCadastro:email").focus();
        return false;
    }
    if (!validaSenha()) {
        document.getElementsByClassName("formCadastro:senha").focus();
        return false;
    }
    if (!validaCsenha()) {
        document.getElementsByClassName("formCadastro:cSenha").focus();
        return false;
    }
   
}
$("#formCadastro\\:nome").blur(validaNome);
$("#formCadastro\\:dataNascimento").blur(validaDataNascimento);
$("#formCadastro\\:cpf").blur(validaCPF);
$("#formCadastro\\:telefone").blur(validaContato);
$("#formCadastro\\:email").blur(validaEmail);
$("#formCadastro\\:senha").blur(validaSenha);
$("#formCadastro\\:cSenha").blur(validaCsenha);

Date.prototype.isValid = function () {
    // An invalid date object returns NaN for getTime() and NaN is the only
    // object not strictly equal to itself.
    return this.getTime() === this.getTime();
};


function validarCPF(cpf) {
    cpf = cpf.replace(/[^\d]+/g, '');
    if (cpf === '')
        return false;
    // Elimina CPFs invalidos conhecidos    
    if (cpf.length !== 11 ||
            cpf === "00000000000" ||
            cpf === "11111111111" ||
            cpf === "22222222222" ||
            cpf === "33333333333" ||
            cpf === "44444444444" ||
            cpf === "55555555555" ||
            cpf === "66666666666" ||
            cpf === "77777777777" ||
            cpf === "88888888888" ||
            cpf === "99999999999")
        return false;
    // Valida 1o digito 
    add = 0;
    for (i = 0; i < 9; i ++)
        add += parseInt(cpf.charAt(i)) * (10 - i);
    rev = 11 - (add % 11);
    if (rev === 10 || rev === 11)
        rev = 0;
    if (rev !== parseInt(cpf.charAt(9)))
        return false;
    // Valida 2o digito 
    add = 0;
    for (i = 0; i < 10; i ++)
        add += parseInt(cpf.charAt(i)) * (11 - i);
    rev = 11 - (add % 11);
    if (rev === 10 || rev === 11)
        rev = 0;
    if (rev !== parseInt(cpf.charAt(10)))
        return false;
    return true;
}

