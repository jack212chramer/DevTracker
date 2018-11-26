	var slideIndex = 1;

Vue.component('gallery', {

		 data:function () {
			    return {
			    	assignementsData:''
			    }
			  },
			  methods:{
			        getData: function(){
			            this.$http.get('getAssignements').then(function(response){
			                this.assignementsData = response.data;
			            }, function(error){
			                console.log(error.statusText);
			            });
			        },
			     // Next/previous controls
			  		plusSlides: function(n)  {
			          this.showSlides(slideIndex += n);
			        },

			        // Thumbnail image controls
			        currentSlide: function (n) {
			          this.showSlides(slideIndex = n);
			        },

			        showSlides: function (n) {
			          var i;
			          var slides = document.getElementsByClassName("mySlides");
			          var dots = document.getElementsByClassName("demo");
			          var captionText = document.getElementById("caption");
			          if (n > slides.length) {slideIndex = 1}
			          if (n < 1) {slideIndex = slides.length}
			          for (i = 0; i < slides.length; i++) {
			            slides[i].style.display = "none";
			          }
			          for (i = 0; i < dots.length; i++) {
			            dots[i].className = dots[i].className.replace(" active", "");
			          }
			          slides[slideIndex-1].style.display = "block";
			          dots[slideIndex-1].className += " active";
			          captionText.innerHTML = dots[slideIndex-1].alt;
			        }
			    },
			    mounted: function () {
			     //   this.getData();
			    	this.showSlides(slideIndex);
			    },
  template: 
	  `
<div class="container bordered">
<h3>Attached images:</h3>
  <!-- Full-width images with number text -->
  <div class="mySlides">
    <div class="numbertext">1 / 6</div>
      <img src="https://thewallpaper.co/wp-content/uploads/2016/02/cute-beagle-dog-full-hd-wallpaper-images-new-best-desktop-background-download-free-cute-doggy-puffy-dogs-1600x1200-768x576.jpg" style="max-width:100%; width:100%;">
  </div>

  <!-- Next and previous buttons -->
  <a class="prev" @click="plusSlides(-1)">&#10094;</a>
  <a class="next" @click="plusSlides(1)">&#10095;</a>

  <!-- Image text -->
  <div class="caption-container">
    <p id="caption"></p>
  </div>

  <!-- Thumbnail images -->
  <div class="row">
    <div class="column">
      <img class="demo cursor bordered miniature" src="https://thewallpaper.co/wp-content/uploads/2016/02/cute-beagle-dog-full-hd-wallpaper-images-new-best-desktop-background-download-free-cute-doggy-puffy-dogs-1600x1200-768x576.jpg" style="width:100%" @click="currentSlide(1)" alt="The Woods">
    </div>

  </div>
  <link rel="stylesheet" type="text/css" href="resources/css/imageGallery.css">
  
<form action="/action_page.php" class="bordered container">
  Select images: <input  type="file" name="img" multiple>
  <input type="submit">
</form>
</div>
   `
})




