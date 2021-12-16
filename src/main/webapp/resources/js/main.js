let canvas=document.getElementById("canvas");
let ctx = canvas.getContext('2d');
let inputY;
let inputX;
let inputR=3;
let isCanvas=false;
$('.form_hidden_r input[type=hidden]').val(inputR);
let yFromCanvas;
let xFromCanvas;


let errorY1= document.createElement("p");
errorY1.textContent = "Значение Y не является числом";
let errorY2= document.createElement("p");
errorY2.textContent = "Значение Y не входит в интервал (-5,3)";
let errorY3= document.createElement("p");
errorY3.textContent = "Введите значение Y";
let errorX=document.createElement("p");
errorX.textContent = "Значение X не выбрано";

const HEIGHT=400;
const WIDTH=800;
canvas.width=WIDTH;
canvas.height=HEIGHT;
const EDOTREZOK=(HEIGHT-50)/10;
drawFigures();
drawPoints();
ctx.fillStyle="#7482ff";


function changeR(link, val) {
    inputR = val;
    $('.form_hidden_r input[type=hidden]').val(inputR);
}



$(document).ready(function() {
    $('.form_hidden_x input[type=hidden]').val(null);
    $('.form_hidden_y input[type=hidden]').val(null);
    $('.form_button_r').on('click', function (event) {
        $('.form_button_r').removeClass("active");
        $(this).addClass('active');
        drawFigures();
        drawPoints();

    });
    $('.form__button_x').click(function(){
        removeError($(".form__button_x"));
        $('.form__button_x').removeClass("active");
        $(this).addClass("active");
        inputX=$(this).val();
        $('.form_hidden_x input[type=hidden]').val(inputX);



    });
    $('.form_text_y').click(function(){
        removeError($(".form_text_y"));
        $(this).addClass("active");

    });


    canvas.addEventListener("mousedown", function (event) {
        $('.form__button_x').removeClass("active");
        xFromCanvas = (event.offsetX - canvas.width / 2) / EDOTREZOK;
        yFromCanvas = -(event.offsetY - canvas.height / 2) / EDOTREZOK;
        drawPoint(event.offsetX, event.offsetY);
        inputX = xFromCanvas.toFixed(2);
        inputY=yFromCanvas.toFixed(2);
        if (inputX>3 || inputX<-5 || inputY>=3 || inputY<=-5){return }
        $('.form_hidden_x input[type=hidden]').val(inputX);
        //isCanvas=true;
        //$('.form_text_y').val(inputY);

        $('.button_submit').click();

        //isCanvas=false;
    });
    $('.button_submit').on('click', function(event) {
        $('.form_hidden_y input[type=hidden]').val(inputY);
        drawFigures();
        drawPoints();
        if (validateForm()) {
            console.log($('.form_hidden_x input[type=hidden]').val()+" "+$('.form_hidden_y input[type=hidden]').val()+" "+$('.form_hidden_r input[type=hidden]').val());
            $('.form_text_y').val("");
            drawPoint(inputX*EDOTREZOK+canvas.width/2, -inputY*EDOTREZOK+canvas.height/2);
            event.preventDefault();
            inputX=null;
            inputY=null;


        } else {
           // $('.input-form__hidden_r input[type=hidden]').val(rval);
        }

    });

    $('.form_text_y').on('input', event => changeY());

    drawPoints();
});



function drawPoints() {
    let rows = [];
    let headers = $(".result_table th");

    $(".result_table tr").each(function (index) {
        let cells = $(this).find("td");
        rows[index] = {};
        cells.each(function (cellIndex) {
            rows[index][$(headers[cellIndex]).html()] = $(this).html().replace(/\s/g, "");
        });
    });
    for (let i = 0; i < rows.length; i++) {

        drawTablePoint(rows[i]["X"]*EDOTREZOK+canvas.width/2, -rows[i]["Y"]*EDOTREZOK+canvas.height/2,rows[i]['Результат'])
    }

}
function changeY(){
    let yField = $('.form_text_y');
    inputY = yField.val().replace(',', '.');
    $('.form_hidden_y input[type=hidden]').val(inputY);
}
function drawTablePoint(x,y,result){
    //console.log("тык: ",x," ",y);
    //console.log(WIDTH,HEIGHT);

    ctx.fillStyle="#4F8A8B";
    ctx.setLineDash([2, 2]);
    ctx.beginPath();
    ctx.moveTo(x, canvas.height/2);
    ctx.lineTo(x, y);
    ctx.moveTo(canvas.width/2, y);
    ctx.lineTo(x, y);
    ctx.stroke();
    ctx.fillStyle = result === 'Промах' ? "#e38585" : "#4F8A8B";
    ctx.arc(x, y, 4, 0, 2 * Math.PI);
    ctx.fill();
    ctx.setLineDash([]);
}



function drawPoint(x,y){
    //console.log("тык: ",x," ",y);
    //console.log(WIDTH,HEIGHT);
    ctx.fillStyle="#4F8A8B";
    ctx.setLineDash([2, 2]);
    ctx.beginPath();
    ctx.moveTo(x, canvas.height/2);
    ctx.lineTo(x, y);
    ctx.moveTo(canvas.width/2, y);
    ctx.lineTo(x, y);
    ctx.stroke();
    ctx.fillStyle = !isInArea() ? "#e38585" : "#4F8A8B";
    ctx.arc(x, y, 4, 0, 2 * Math.PI);
    ctx.fill();
    ctx.setLineDash([]);
}
function clearCanvas(){
    canvas.getContext('2d').clearRect(0, 0, canvas.width, canvas.height);
}

function drawFigures(){
    clearCanvas();
    const RADIUS=(HEIGHT-50)/10*inputR/2;


    //квадрат
    ctx.fillStyle="#FFCB74";
    ctx.fillRect(WIDTH/2, HEIGHT/2-2*RADIUS, 2*RADIUS, 2*RADIUS);

    //треугольник
    ctx.beginPath();
    ctx.moveTo(WIDTH/2,HEIGHT/2);
    ctx.lineTo(WIDTH/2,HEIGHT/2-RADIUS);
    ctx.lineTo(WIDTH/2-RADIUS,HEIGHT/2);
    ctx.fill();

    ctx.beginPath();
    ctx.moveTo(WIDTH/2,HEIGHT/2);
    ctx.lineTo(WIDTH/2,HEIGHT/2+2*RADIUS);
    ctx.lineTo(WIDTH/2-2*RADIUS,HEIGHT/2);
    ctx.fill();

//окружность
    ctx.arc(WIDTH/2,HEIGHT/2,2*RADIUS,Math.PI/2,Math.PI);
    ctx.fill();



    //ось ординат

    ctx.beginPath();
    ctx.moveTo(WIDTH/2,0);
    ctx.lineTo(WIDTH/2,HEIGHT);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(WIDTH/2,0);
    ctx.lineTo(WIDTH/2-10,15);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(WIDTH/2,0);
    ctx.lineTo(WIDTH/2+10,15);
    ctx.stroke();

    //10 палочек на оси
    for (let i=-5;i<=5;i++ ){
        ctx.beginPath();
        ctx.moveTo(WIDTH/2-5,HEIGHT/2+i*EDOTREZOK);
        ctx.lineTo(WIDTH/2+5,HEIGHT/2+i*EDOTREZOK);
        ctx.stroke();
        ctx.strokeText(String(-i),WIDTH/2+5,HEIGHT/2+i*EDOTREZOK);
    }


    //ось абсцисс
    ctx.beginPath();
    ctx.moveTo(WIDTH*2/10,HEIGHT/2);
    ctx.lineTo(8*WIDTH/10,HEIGHT/2);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(WIDTH*8/10,HEIGHT/2);
    ctx.lineTo(8*WIDTH/10-15,HEIGHT/2-10);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(WIDTH*8/10,HEIGHT/2);
    ctx.lineTo(8*WIDTH/10-15,HEIGHT/2+10);
    ctx.stroke();

    //10 палочек на оси
    for (let i=-5;i<=5;i++ ){
        ctx.beginPath();
        ctx.moveTo(WIDTH/2-i*EDOTREZOK,HEIGHT/2+5);
        ctx.lineTo(WIDTH/2-i*EDOTREZOK,HEIGHT/2-5);
        ctx.stroke();
        ctx.strokeText(String(-i),WIDTH/2-i*EDOTREZOK,HEIGHT/2-5);

    }
}

function validateForm() {
    return validateX() & validateY() ;
}
function validateX() {

    if (inputX) {
        errorX.remove();
        removeError($(".form__button_x"));
        return true;
    } else {
        $('#errors').append(errorX);
        error($(".form__button_x"));
        return false;
    }
}

function validateY() {
    const Y_MIN = -5;
    const Y_MAX = 3;
// if (isCanvas==false){
//     let yField = $('.form_text_y');
//     inputY = yField.val().replace(',', '.');
// }

    if(inputY==""){
        errorY1.remove();
        errorY2.remove();
        $('#errors').append(errorY3);
        error($('.form_text_y'));
        //document.getElementById('y').classList.add('red');
        return false;
    }else{
        if ((/[^0-9.-]/i.test(inputY))){
            errorY2.remove();
            errorY3.remove();
            $('#errors').append(errorY1);
            error($('.form_text_y'));
            //document.getElementById('y').classList.add('red');
            return false;
        }else{

            if (inputY > Y_MIN && inputY < Y_MAX){
                errorY1.remove();
                errorY2.remove();
                errorY3.remove();
                removeError($('.form_text_y'));
                //document.getElementById('y').classList.remove('red');
                return true;
            }else{
                errorY1.remove();
                errorY3.remove();
                $('#errors').append(errorY2);
                error($('.form_text_y'));
                //document.getElementById('y').classList.add('red');
                return false;
            }
        }

    }


}

function error(elem){
    elem.css("border","5px solid #e38585");
    elem.css("box-shadow", "inset 0px 0 5px 3px #e38585");
}
function removeError(elem){
    elem.css("border","2px solid #729496");
    elem.css("box-shadow", "");
}
function isInArea(){
    return checkTriangle() || checkRectangle() || checkCircle();
}
function checkTriangle(){
    return inputX <= 0 && inputY >= 0 && inputY <= inputX + inputR/2;
}
function checkRectangle(){
    return inputX >= 0 && inputY >= 0 && inputX<=inputR && inputY<=inputR;
}
function checkCircle(){
    return inputX <= 0 && inputY <= 0 && inputY*inputY<=inputR*inputR-inputX*inputX;
}