const DELAY=7*1000;
let dateForm=document.getElementById("date");
let time=document.getElementById("time");
dateForm.textContent=new Date().toLocaleDateString().toString();
time.textContent=new Date().toLocaleTimeString().toString();
setInterval(setCurrentDateTime, DELAY);


function setCurrentDateTime() {
        let date = new Date();
        dateForm.textContent=date.toLocaleDateString().toString();
        time.textContent=date.toLocaleTimeString().toString();
}

