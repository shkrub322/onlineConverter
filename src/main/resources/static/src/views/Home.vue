<template>

  <carousel-3d :perspective="180" :controls-visible="true" :width="1450" :height="757":display="1" border="0">
    <slide :index="0">
      <img src="images/notebook-2386034.jpg"/>
    </slide>
    <slide :index="1">
      <img src="images/urban-438393_1280.jpg"/>
    </slide>
    <slide :index="2">
      <img src="images/money-2212965_1280.jpg"/>
    </slide>
  </carousel-3d>


</template>

<script>
// @ is an alias to /src
import { Carousel3d, Slide } from 'vue-carousel-3d';

export default {
  name: "Home",
  components: {
    Carousel3d,
    Slide
  }
};
</script>

<style scoped>
.carousel-3d-container {
  margin-top: 10px;
  width: 100%;
  height: 100%;
  .carousel-3d-slide {
    padding: 10px;
    .title { font-size: 22px; }
  }
}
img{
  box-shadow: 0 9px 9px -7px rgba(0,0,0,.3);
}
carousel-3d{
  margin-top: 10px;
  margin-bottom: 0px;
}
</style>