<template>
	<header>
		<div class="container">
			<div class="row">
				<div>
					<router-link to="/"><img src="images/OC_Logo.png" alt="Logo" class="logo" title="OnlineConverter"></router-link>
				</div>
				<div class="header_best_currency">
					<div class="header_best_currency_title">
						<span class="left">Лучшие курсы</span>
						<span class="right">покупка | продажа</span>
					</div>
					<div class="header_best_currency_content">
						<span class="box">
							<img src="images/flag-usa.png" alt="usa" class="flag"/>USD
							<p>{{this.usd_p}} | {{this.usd_s}}</p>
						</span>
						<span class="box">
							<img src="images/flag-eurosoyuza-kupit-kiev-max-500.jpg" alt="eur" class="flag"/>EUR
							<p>{{this.eur_p}} | {{this.eur_s}}</p></span>
						<span class="box">
							<img src="images/flag-big.png" alt="rus" class="flag"/>RUB
							<p>{{this.rub_p}} | {{this.rub_s}}</p>
						</span>
					</div>
				</div>
			</div>
		</div>

		<div class="main-nav">
			<div class="container">
				<div class="row">
					<span class="logo">
						<router-link to="/"><img src="images/OC_Logo(mini).png" alt="" title="OnlineConverter" alt="Logo"/></router-link>
					</span>
					<router-link class="menu" to="/courses"><span>Курсы валют</span></router-link>
				</div>
			</div>
		</div>
	</header>
</template>

<script>
	import axios from 'axios';

	export default{
		name: "Header",
		data: function(){
			return{
				usd_p: null,
				usd_s: null,
				eur_p: null,
				eur_s: null,
				rub_p: null,
				rub_s: null
			}
		},
		created: function(){
			axios.get('http://localhost:3000/rates/best?from=USD&to=BYN').then(response=> (this.usd_p=response.data));
	        axios.get('http://localhost:3000/rates/best?from=BYN&to=USD').then(response=> (this.usd_s=response.data));
	        axios.get('http://localhost:3000/rates/best?from=EUR&to=BYN').then(response=> (this.eur_p=response.data));
	        axios.get('http://localhost:3000/rates/best?from=BYN&to=EUR').then(response=> (this.eur_s=response.data));
	        axios.get('http://localhost:3000/rates/best?from=RUB&to=BYN').then(response=> (this.rub_p=response.data));
	        axios.get('http://localhost:3000/rates/best?from=BYN&to=RUB').then(response=> (this.rub_s=response.data));
		},
		mounted: function () {
	        var options = {
	            offset: 150
	        };
        	var header = new Headhesive('.main-nav', options);
		}
	};
</script>

<style scoped>
header{
    background-color: #FFFFFF;
}
.logo{

}
.converter{
	float: right;
}
.header_best_currency{
    border-left: #c9c9c9 solid 1px;
    padding: 10px 10px 0px;
}
.header_best_currency_title{
    text-transform: uppercase;
    width: 255px;
    height: 21px;
}
.left{
    float: left;
}
.right{
    margin-top: 5px;
    font-size: 11px;
    color: #999999;
    float: right;
}
.header_best_currency_content{
    display: flex;
    margin-top: 12px;
}
.box{
    font-size: 12px;
    width: 85px;
    height: 50px;
}
.flag{
    margin-right: 4px;
    width: 18px;
    height: 12px;
}
.main-nav{
    position: relative;
    background-color: #a52319;
    box-shadow: 0 9px 9px -7px rgba(0,0,0,.3);
}
.main-nav .logo{
    display: none;
    height: 0;
    width: 0;
}
.main-nav.a {
    height: 100px
}
.menu{
    font-size: 20px;
    color: white;
    display: inline;
    padding: 15px;
}
.menu:hover{
    background-color: rgba(212, 148, 143, 0.49);
    cursor: pointer;
}
img{
	width: 100%;
}
.headhesive{
    background-color: white;
    position: fixed;
    width: 100%;
    transform: translateY(-100%);
    transition: 0.3s;
}
.headhesive--stick{
    transform: translateY(0);
    z-index: 100;
}
.headhesive--stick .menu{
    color: #a52319; 
}
.headhesive--stick .menu:hover{
    cursor: pointer;
    background-color: #a52319;
    color: white;
}
.headhesive--stick .logo{
    display: block;
    opacity: 1;
    width: 95px;
    height: 50px;
    margin-right: 15px;
    padding: 5px;
}
.main-nav .menu .router-link-exact-active{
	background-color: rgba(212, 148, 143, 0.49);	
}
.headhesive--stick .menu .router-link-exact-active{
	background-color: #a52319;
	color: white;
}
a{
	outline: none;
}
a:hover{
	text-decoration: none;
}
</style>
