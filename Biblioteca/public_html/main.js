/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


$(document).ready(function () {
    $('#TextBoxId').keypress(function (e) {
        if (e.keyCode == 13)
            $('#linkadd').click();
    });
});