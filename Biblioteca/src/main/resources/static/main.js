/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


$(document).ready(function () {
    $('#TextBoxId').keypress(function (e) {
        if (e.keyCode == 13)
            $('#linkadd').click();
    });
});

function button5() {
    carregarEmp();
}

function button6() {
    let livroid = $("#Bplivroid");
    let rg = $("#Bprg");

    if (livroid.val().length >= 1 && rg.val().length >= 1) {
        cadastrarEmprestimo(livroid.val(), rg.val());
    }
}

function button1() {
    let username = $('#username').val();
    let pass = $('#senha').val();
    let rg = $('#rg').val();
    let telefone = $('#telefone').val();

    if (username.length <= 1) {
        alert("Verifique o username");
    } else {
        if (rg.length <= 7) {
            alert("Verifique o rg/cpf coloque pelomenos 8 digitos");
        } else {
            if (pass.length <= 3) {
                alert("Verifique a senha!");
            } else {
                rgUser(username, pass, rg, telefone);
            }
        }
    }
}

function button2() {
    let pl = $('#PESQUISARLIVROS').val();
    if (pl.length >= 1) {
        carregarLivros(pl);
    }
}

function carregarEmp() {
    $.ajax({
        url: 'http://localhost:8080/Biblioteca/emprestimos',
        method: 'GET',
        success: function (data) {
            $('#tabela').remove(); //CLEAR
            $("#main").append("<div id='tabela'></div>"); //CLEAR

            for (i = 0; i < data.length; i++) {
                let emp = data[i];
                $('#tabela').append("<div style='margin: 5px;'> <span>" + emp.livroid + " - " + emp.rg + "</span> <a style='background-color:#ededed; padding: 7px; display: inline-block width: 100px; height: 20px; border-radius: 3px; border-style: none; text-decoration: none; cursor: default; color: black; cursor: pointer;' class='" + emp.id + "' id='bDeletar' onclick='deletarEmp(" + emp.id + ")'> Excluir </a> </div>");
                $('#tabela').append("<br>");
            }
        },
        error: function () {
            alert('Não foi possível carregar empréstimos da API..');
        }
    });
}

function deletarEmp(id) {
    deletarEmp($('#bDeletar').attr('class'));
}

function deletarEmp(id) {
    $.ajax({
        url: 'http://localhost:8080/Biblioteca/deletar/' + id,
        method: 'DELETE',
        success: function (data) {
            alert("Empréstimo deletado com sucesso!");
        },
        error: function () {
            alert('Não foi possível deletar o empréstimo..');
        }
    });
}
function carregarLivros(title) {
    $.ajax({
        url: 'http://localhost:8080/Biblioteca/livro/' + title,
        method: 'GET',
        success: function (data) {
            $('#tabela').remove(); //CLEAR
            $("#main").append("<div id='tabela'></div>"); //CLEAR

            for (i = 0; i < data.length; i++) {
                let livro = data[i];
                $('#tabela').append("<a style='text-decoration: none; cursor: default; color: black;' href='/livro/" + livro.id + "'>" + livro.title + "</a>");
                $('#tabela').append("<br>");
            }
        },
        error: function () {
            alert('Não foi possível carregar livros da API..');
        }
    });
}

function cadastrarUsuario(username, pass, rg, telefone) {
    $.ajax({
        url: 'http://localhost:8080/Biblioteca/cadastrar',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            username: username,
            pass: pass,
            rg: rg,
            telefone: telefone
        }),
        success: function (data) {
            alert("Usuário registrado com sucesso!");
        },
        error: function () {
            alert('Não foi possível registrar usuário..');
        }
    });
}

function rgUser(username, pass, rg, telefone) {
    $.ajax({
        url: 'http://localhost:8080/Biblioteca/user/' + rg,
        method: 'GET',
        contentType: 'application/json',
        data: rg,
        success: function (data) {
            alert("Dados já cadastrados no banco de dados!");
        },
        error: function () {
            cadastrarUsuario(username, pass, rg, telefone);
        }
    });
}

function button3() {
    let titulo = $('#Btitulo');
    let subtitulo = $('#Bsubtitulo');
    let author = $('#Bauthor');
    let descr = $('#Bdescr');

    if (titulo.val().length >= 1 && subtitulo.val().length >= 1 && author.val().length >= 1 && descr.val().length >= 1) {
        cadastrarLivro(titulo.val(), subtitulo.val(), author.val(), descr.val());
        titulo.val('');
        subtitulo.val('');
        author.val('');
        descr.val('');
    }
}

function cadastrarLivro(titulo, subtitulo, author, descr) {
    $.ajax({
        url: 'http://localhost:8080/Biblioteca/novo-livro',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            title: titulo,
            subtitle: subtitulo,
            author: author,
            descr: descr
        }),
        success: function (data) {
            alert("Livro registrado com sucesso!");
        },
        error: function () {
            alert('Não foi possível registrar livro..');
        }
    });
}

function cadastrarEmprestimo(livroid, rg) {
    $.ajax({
        url: 'http://localhost:8080/Biblioteca/novo-emprestimo',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            livroid: livroid,
            rg: rg
        }),
        success: function (data) {
            alert("Empréstimo registrado com sucesso!");
        },
        error: function () {
            alert('Não foi possível registrar o empréstimo..');
        }
    });
}
function button4() {
    let titulo = $('#Ptitulo');
    let subtitulo = $('#Psubtitulo');
    let author = $('#Pauthor');
    let texto = $('#texto');

    if (titulo.val().length >= 1 && subtitulo.val().length >= 1 && author.val().length >= 1 && texto.val().length >= 1) {
        cadastrarPostagem(titulo.val(), subtitulo.val(), author.val(), texto.val());
        titulo.val('');
        subtitulo.val('');
        author.val('');
        texto.val('');
    }
}

function cadastrarPostagem(titulo, subtitulo, author, texto) {
    $.ajax({
        url: 'http://localhost:8080/Biblioteca/nova-postagem',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            titulo: titulo,
            subtitulo: subtitulo,
            author: author,
            texto: texto
        }),
        success: function (data) {
            alert("Postagem registrada com sucesso!");
        },
        error: function () {
            alert('Não foi possível registrar postagem..');
        }
    });
}