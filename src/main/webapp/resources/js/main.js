let canvas=document.getElementById("canvas");
let ctx = canvas.getContext('2d');
let inputY;
let inputX;
let inputR=3;
let yFromCanvas;
let xFromCanvas;

const HEIGHT=400;
const WIDTH=800;
canvas.width=WIDTH;
canvas.height=HEIGHT;
const EDOTREZOK=(HEIGHT-50)/10;
drawFigures();
ctx.fillStyle="#7482ff";


function changeR(link, val) {
    inputR = val;
    $('.form_hidden_r input[type=hidden]').val(inputR);
}


$(document).ready(function() {
    // $('.r-button').click(function(){
    //     $('.r-button').removeClass("active");
    //     $(this).addClass("active");
    //     inputR=document.getElementsByClassName("active")[0].value;
    //     errorR.remove();
    //     removeError($('#r'));
    //     drawFigures();
    //     drawPoints();
    // });


    canvas.addEventListener("mousedown", function (event) {

        // if (!validateR()){
        //     window.scrollBy(0,300);
        //     return;
        // }
        xFromCanvas = (event.offsetX - canvas.width / 2) / EDOTREZOK;
        yFromCanvas = -(event.offsetY - canvas.height / 2) / EDOTREZOK;
        drawPoint(event.offsetX, event.offsetY);
        inputX = xFromCanvas.toFixed(2);
        //sendCheckAreaRequest(inputX, yFromCanvas, inputR);
    });
});

function drawPoints(){

    let pointX = Array.from(document.getElementsByClassName("coordX")).map(v => v.innerHTML);
    let pointY = Array.from(document.getElementsByClassName("coordY")).map(v => v.innerHTML);
    for (let i=0;i<pointX.length;i++){
        drawPoint(pointX[i]*EDOTREZOK+canvas.width/2,-pointY[i]*EDOTREZOK+canvas.height/2);
    }
}

function drawPoint(x,y){
    console.log("тык: ",x," ",y);
    console.log(WIDTH,HEIGHT);
    ctx.fillStyle="#4F8A8B";
    ctx.setLineDash([2, 2]);
    ctx.beginPath();
    ctx.moveTo(x, canvas.height/2);
    ctx.lineTo(x, y);
    ctx.moveTo(canvas.width/2, y);
    ctx.lineTo(x, y);
    ctx.stroke();
    ctx.fillStyle = "#e38585";
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


