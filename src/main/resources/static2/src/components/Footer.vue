<template>
	<footer>
		<div class="container">
			<div class="row outer">
				<div>
					<router-link to="/"><img class="logo" src="images/OC_Logo.png" alt="Logo"/></router-link>
				</div>
				<div class="col-5 social">
					<div><p>Оставайся с ОС</p></div>
					<div class="row icons ">
						<img src="images/facebook.png" alt="facebook">
						<img src="images/Instagram.png" alt="instagram">
						<img src="images/twitter.png" alt="twitter">
					</div>
				</div>
			</div>
			<div class="row info outer">
				<div class="col-2">
					<p>©2018, OC.by</p>
				</div>
				<div class="col-3 name">
					<p>Дизайн сайта - Шкрабков Владислав</p>
				</div>
			</div>
		</div>
	</footer>
</template>

<script>
	export default{
		name: "Footer",
	};
</script>

<style scoped>
	footer{
		margin-top: -20px;
		background-color: white;
	}
	.logo{
		margin-top: 10px;
	}
	.outer{
		border-bottom: 1px solid #EBEBEB;
	}
	.social{
		display: flex;
		float: right;
		margin-left: auto;
		margin-right: 0px;
	}
	.social div{
		margin-top: auto;
		margin-bottom: auto;
	}
	.social p{
		margin-left: auto;
		margin-right: 10px;
	}
	p{
		color: #999999;
		font-size: 12px;
		margin: 0;
	}
	.icons{
		margin-left: auto;
		margin-right:20px;
		width: auto;
	}
	.icons img{
		margin-right: 5px;
	}
	.info{
		margin-top: 40px;
	}
	.info div{
		margin-top: auto;
		margin-bottom: 0;
	}
	.name{
		margin-left: auto;
		margin-right: 0;
	}
	a{
	outline: none;
	}
</style>