/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var campoNome = document.getElementById("campoNome");
var campoSexo = document.getElementById("campoSexo");
var campoDataNascimento = document.getElementById("campoDataNascimento");
var campoCpf = document.getElementById("campoCpf");
var campoRg = document.getElementById("campoRg");
var campoEmail = document.getElementById("campoEmail");
var campoSenha = document.getElementById("campoSenha");
var campoCsenha = document.getElementById("campoCsenha");


function validaNome() {
    var valorNome = document.getElementById("nome").value;
    if (valorNome === "") {
        campoNome.classList.add("error");
        return false;
    } else {
        campoNome.classList.remove("error");
        campoNome.classList.add("success");
        return true;
    }

}
function validaSexo() {
    var valorSexo = document.getElementById("sSexo");
    if (valorSexo.options[valorSexo.selectedIndex].value === "") {
        campoSexo.classList.add("error");
        return false;
    } else {
        campoSexo.classList.remove("error");
        campoSexo.classList.add("success");
        return true;
    }
}
function validaDataNascimento() {
    var valorData = new Date(document.getElementById("dataNascimento").value);
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
    var valorDoCampo = $("#cpf").val();
    if (!reg.test(valorDoCampo) || !validarCPF(valorDoCampo)) {
        campoCpf.classList.add("error");
        return false;
    } else {
        campoCpf.classList.remove("error");
        campoCpf.classList.add("success");
        return true;
    }
}
function validaRg() {
    var reg = /^[0-9]{2}.?[0-9]{3}.?[0-9]{3}-{1}/;
    var valorDoCampo = $("#rg").val();
    if (!reg.test(valorDoCampo)) {
        campoRg.classList.add("error");
        return false;
    } else {
        campoRg.classList.remove("error");
        campoRg.classList.add("success");
        return true;
    }

}
function validaEmail() {
    var regex = /^.+@.+\..{2,3}$/;
    var valorDoCampo = $("#email").val();
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
    var senha = document.getElementById("senha").value;

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
    var cSenha = document.getElementById("cSenha").value;
    var senha = document.getElementById("senha").value;

    if (senha !== cSenha) {
        campoCsenha.classList.add("error");
        return false;
    } else {
        campoCsenha.classList.remove("error");
        campoCsenha.classList.add("success");
        return true;
    }
}
function validaCadastro(form) {
    if (!validaNome()) {
        form.nome.focus();
        return false;
    }
    if (!validaSexo()) {
        form.sexo.focus();
        return false;
    }
    if (!validaDataNascimento()) {
        form.dataNascimento.focus();
        return false;
    }
    if (!validaCPF()) {
        form.cpf.focus();
        return false;
    }
    if (!validaRg()) {
        form.rg.focus();
        return false;
    }
    if (!validaEmail()) {
        form.email.focus();
        return false;
    }
    if (!validaSenha()) {
        form.senha.focus();
        return false;
    }
    if (!validaCsenha()) {
        form.cSenha.focus();
        return false;
    }
    if (!confirm('Tem certeza que deseja efetuar o registro com essas informações?')) {
        return false;
    } else {
        alert('Operação realizada com sucesso!');
    }
}
$("#nome").blur(validaNome);
$("#sSexo").blur(validaSexo);
$("#dataNascimento").blur(validaDataNascimento);
$("#cpf").blur(validaCPF);
$("#rg").blur(validaRg);
$("#email").blur(validaEmail);
$("#senha").blur(validaSenha);
$("#cSenha").blur(validaCsenha);

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

