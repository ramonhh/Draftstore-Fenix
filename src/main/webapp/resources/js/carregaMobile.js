function sizeOfThings() {
    var windowWidth = window.innerWidth;
    var windowHeight = window.innerHeight;

    var screenWidth = screen.width;
    var screenHeight = screen.height;

    if (windowWidth <= 500 && windowHeight <= 540) {

        location.href = "http://localhost:8080//WebMobile/detalheMobile.html"

    }else if (windowWidth >= 501 && windowHeight >= 541) {
        location.href = "http://localhost:8080//WebMobile/detalhe.html"
    }
};

window.addEventListener('resize', function () {
    sizeOfThings();
});