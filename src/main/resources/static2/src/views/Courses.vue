<template>
	<div class="list" v-if="another">
		<div class="container block list-content" v-if="list">
			<p class="back"  @click="hiddenList">Назад</p>
			<div class="row">
				<h3><b>Выберите свой населенный пункт</b></h3>
				<div class="container items">
					<div class="row">
					<div class="col-lg-2 col-md-4 col-xs-6 " v-for="city in cities"><span class="title row"><b>{{city[0].region.name}}</b></span>
						<div v-for="name in city" @click="setCity(name.id, name.name)"><p>{{name.name}}</p></div>
					</div>
				</div>
				</div>
			</div>
		</div>
		<div class="container block block-table" v-if="depTable">
			<p class="back" @click="hiddenList">Назад</p>
			<div class="conv" v-if="showConv">
				<div class="close"><img src="images/icons8-cancel-20.png" alt="close" @click="closeConv"></div>
				<table class="table">
					<thead>
						<tr>
							<th><p>{{from}}</p></th>
							<th><p>{{to}}</p></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" name="val" v-model="leftInput"/></td>
							<td><p>{{result}}</p></td>
						</tr>
					</tbody>
				</table>
			</div>	
			<table class="table departments">
				<thead>
					<tr>
						<th>Отделение</th>
						<th>USD <br>Покупка</th>
						<th>Продажа</th>
						<th>EUR<br>Покупка</th>
						<th>Продажа</th>
						<th>RUB<br>Покупка</th>
						<th>Продажа</th>
						<th>EUR&#8594;<br>Покупка</th>
						<th>USD<br>Продажа</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="rate in rates">
						<td>{{rate[0].department.name}}<br><i>{{rate[0].department.address}}</i></td>
						<td>{{rate[0].value}}<br><img src="images/calc.png" alt="calculator" @click="converter(rate[0].value, false, 'USD', 'BYN')"></td>
						<td>{{rate[1].value}}<br><img src="images/calc.png" alt="calculator" @click="converter(rate[1].value, true, 'BYN', 'USD')"></td>
						<td>{{rate[2].value}}<br><img src="images/calc.png" alt="calculator" @click="converter(rate[2].value, false, 'EUR', 'BYN')"></td>
						<td>{{rate[3].value}}<br><img src="images/calc.png" alt="calculator" @click="converter(rate[3].value, true, 'BYN', 'EUR')"></td>
						<td>{{rate[4].value}}<br><img src="images/calc.png" alt="calculator" @click="converter(rate[4].value, false, 'RUB', 'BYN')"></td>
						<td>{{rate[5].value}}<br><img src="images/calc.png" alt="calculator" @click="converter(rate[5].value, true, 'BYN', 'RUB')"></td>
						<td>{{rate[6].value}}<br><img src="images/calc.png" alt="calculator" @click="converter(rate[6].value, false, 'EUR', 'USD')"></td>
						<td>{{rate[7].value}}<br><img src="images/calc.png" alt="calculator" @click="converter(rate[7].value, true, 'USD', 'EUR')"></td>			
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div id="courses" v-else>
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-md-12 col-lg-9  block">
					<div class="row top">
						<span class="row">
							<router-link to="/"><p>Главная </p></router-link>
							<p>&#8594; Курсы валют в г. {{city}} на сегодня</p>
						</span>
					</div>
					<div class="row middle">
						<span><h2><b>Курсы валют банков г. {{city}} на {{formatDate}}</b></h2></span>
					</div>
					<div class="row">
						<table class="table best-currency">
							<thead>
								<tr>
									<th>Валюта</th>
									<th>Покупка</th>
									<th>Продажа</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Доллар США</td>
									<td>{{usd_p}}</td>
									<td>{{usd_s}}</td>
								</tr>
								<tr>
									<td>Евро</td>
									<td>{{eur_p}}</td>
									<td>{{eur_s}}</td>
								</tr>
								<tr>
									<td>Российский рубль</td>
									<td>{{rub_p}}</td>
									<td>{{rub_s}}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="col-md-6 col-lg-3 ">
					<div class="block best_conv">
						<p><b>Конвертер по наилучшему курсу в г. {{city}}</b></p>
						<div class="container">
							<div class="row">
								<div class="col-7"><input type="text" name="val" v-model="inputVal"></div>
								<div class="col-2">
									<select v-model="from">
										<option>USD</option>
										<option>EUR</option>
										<option>BYN</option>
										<option>RUB</option>
									</select>
								</div>
							</div>
						</div>
						<div class="container">
							<div class="row">
								<div class="col-7"><p>{{Math.round(inputVal * val * 100) / 100}}</p></div>
								<div class="col-2">
									<select v-model="to">
										<option v-for="currency in currencies">{{currency}}</option>	
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="container block">
			<div class="row cities">
				<div class="city" v-for="city in mainCities" @click="setCity(city.id, city.name)">
					{{city.name}}
				</div>
				<div class="city" v-if="difCity">{{city}}</div>
				<div class="city" @click="showList">Другой город</div>
			</div>
			<div class="row middle"><span><h4><b>Курсы валют в г. {{city}} на сегодня</b></h4></span></div>
			<table class="table bank-currency">
				<thead>
					<tr>
						<th>Банк</th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="bank in banks" @click="setDepartment(bank.id, currentCityId)">
						<td >{{bank.name}}</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</template>

<script>
	import axios from 'axios';

	export default{
		data: function(){
			return{
				formatDate: null,
				city: '',
				cityId: null,
				difCity: '',
				banks: [],
				mainCities: [],
				cities: [],
				showCitiesF: false,
				usd_p: null,
				usd_s: null,
				eur_p: null,
				eur_s: null,
				rub_p: null,
				rub_s: null,
				another: false,
				list: false,
				depTable: false,
				departments: [],
				showDep: false,
				rates: [],
				showConv: false,
				leftInput: '',
				result: '',
				from: '',
				to: '',
				toVal: null,
				currencies: [],
				val: null,
				inputVal: null
			}
		},
		created: function(){
			this.city = 'Минск';
			this.list = false;
			this.depTable = false;
			let date = new Date();
			this.formatDate = date.getDate() + '.' + date.getMonth() + '.' + date.getFullYear();
			axios.get('http://localhost:3000/cities/main')
					.then(response=>{
						this.mainCities = response.data;
						for (let i = 0; i < this.mainCities.length; i++){
							if (this.mainCities[i].name === this.city){
								let id = this.mainCities[i].id;
								this.currentCityId = id;
								axios.get('http://localhost:3000/banks/city/' + id)
									.then(response=>(this.banks = response.data));
								axios.get('http://localhost:3000/rates/bestByCity?from=USD&to=BYN&cityId=' + id)
									.then(response=>(this.usd_p=response.data));
								axios.get('http://localhost:3000/rates/bestByCity?from=BYN&to=USD&cityId=' + id)
									.then(response=>(this.usd_s=response.data));
								axios.get('http://localhost:3000/rates/bestByCity?from=EUR&to=BYN&cityId=' + id)
									.then(response=>(this.eur_p=response.data));
								axios.get('http://localhost:3000/rates/bestByCity?from=BYN&to=EUR&cityId=' + id)
									.then(response=>(this.eur_s=response.data));
								axios.get('http://localhost:3000/rates/bestByCity?from=RUB&to=BYN&cityId=' + id)
									.then(response=>(this.rub_p=response.data));
								axios.get('http://localhost:3000/rates/bestByCity?from=BYN&to=RUB&cityId=' + id)
									.then(response=>(this.rub_s=response.data));
							}
						}
					});
		},
		methods: {
			setCity: function(cityId, name){
				if (this.list == true){
					this.difCity = name;
				} else{
					this.difCity = '';
				}
				this.another = false;
				this.currentCityId = cityId;
				this.list = false;
				this.depTable = false;
				this.city = name;
				axios.get('http://localhost:3000/banks/city/' + cityId)
					.then(response=>(this.banks = response.data));
				axios.get('http://localhost:3000/rates/bestByCity?from=USD&to=BYN&cityId=' + cityId)
					.then(response=>(this.usd_p=response.data));
				axios.get('http://localhost:3000/rates/bestByCity?from=BYN&to=USD&cityId=' + cityId)
					.then(response=>(this.usd_s=response.data));
				axios.get('http://localhost:3000/rates/bestByCity?from=EUR&to=BYN&cityId=' + cityId)
					.then(response=>(this.eur_p=response.data));
				axios.get('http://localhost:3000/rates/bestByCity?from=BYN&to=EUR&cityId=' + cityId)
					.then(response=>(this.eur_s=response.data));
				axios.get('http://localhost:3000/rates/bestByCity?from=RUB&to=BYN&cityId=' + cityId)
					.then(response=>(this.rub_p=response.data));
				axios.get('http://localhost:3000/rates/bestByCity?from=BYN&to=RUB&cityId=' + cityId)
					.then(response=>(this.rub_s=response.data));
			},
			showList: function(){
				this.cities = [];
				this.another = true;
				this.list = true;
				axios.get('http://localhost:3000/regions')
					.then(response=>{
						let regions = response.data;
						for (let i = 0; i < regions.length; i++){
							axios.get('http://localhost:3000/cities/' + regions[i].id)
								.then(response=>this.cities.push(response.data));
						}
					});
			},
			hiddenList: function(){
				this.depTable = false;
				this.another = false;
				this.list = false;
			},
			setDepartment: function(bankId, cityId){
				this.another = true;
				this.depTable = true;
				this.showDep = !this.showDep;
				axios.get('http://localhost:3000/departments/city', {
					params: {
						bank: bankId,
						city: cityId
					}
				}).then(response=>{
					this.rates = [];
					this.departments=response.data;
					for (let i = 0; i < this.departments.length; i++){
						axios.get('http://localhost:3000/rates?department=' + this.departments[i].id)
							.then(response=>this.rates.push(response.data));
					}
				});
			},
			converter: function(coef, reverse, from, to){
				this.showConv = true;
				this.leftInput = '';
				if (from === 'RUB' || to === 'RUB'){
					coef /= 100;
				}
				if (reverse){
					this.coef = 1 / coef;
				} else{
					this.coef = coef;
				}
				this.from = from;
				this.to = to;
			},
			closeConv: function(){
				this.showConv = false;
				this.leftInput = '';
			}
		},
		watch: {
			leftInput: function(){
				this.result = Math.round(this.leftInput * this.coef * 100) / 100;
			},
			from: function(){
				this.toVal = '';
            	switch (this.from) {
	                case 'USD': this.currencies = ['EUR', 'BYN'];
	                    break;
	                case 'EUR': this.currencies = ['USD', 'BYN'];
	                    break;
	                case 'BYN': this.currencies = ['USD', 'EUR', 'RUB'];
	                    break;
	                case 'RUB': this.currencies = ['BYN'];
	                    break;
            	}
			},
			to: function () {
	            this.val = 0;
	            axios.get('http://localhost:3000/rates/bestByCity', {
	            	params:{
	            		from: this.from,
	            		to: this.to,
	            		cityId: this.currentCityId
	            	}
	            }).then(response=>{
	            	this.val=response.data;
	            	if(this.from === 'RUB' || this.to === 'RUB'){
	            		this.val /= 100;
	            	}
	            	if (this.from === 'BYN' || (this.from === 'USD' && this.to === 'EUR')){
	            		this.val = 1/this.val;
	            	}
	            });
	      	}
	  	}
	};
</script>

<style scoped>
	p{
		margin: 0;
	}
	.list{
		min-height: 90vh;
		padding-bottom: 20px;
	}
	.list .container{
		box-shadow: none;
	}
	.list-content{
		padding: 15px;
	}
	.list-content .row{
		padding: 0;
		margin-left: 0px;
	}
	.title{
		margin-top: 10px;
		height: 30px;
		margin-bottom: 10px
	}
	.items{
		font-size: 15px;
		display: flex;
	}
	.conv{
		border-radius: 10px;
		position: fixed;
		width: 250px;
		height: 150px;
		background-color: #546074;
	}
	.conv p{
		color: white;
	}
	input{
		width: 85px;
	}
	.conv thead th{
		border: 0px;
	}
	.conv tbody td{
		border: 0px;
	}
	.close{
		margin-left: auto;
		margin-right: 5px;
	}
	#courses{
		min-height: 90vh;
		padding-bottom: 10px;
	}
	.block{
		background-color: white;
		box-shadow: 0 9px 9px -7px rgba(0,0,0,.3);
		margin-top: 10px;
		margin-bottom: 10px;
	}
	.block-table{
		overflow: scroll;
	}
	.col-3{
		padding: 0;
	}
	.top{
		border-bottom: 1px solid #EBEBEB;
	}
	.middle{
		display: flex;
	}
	span{
		padding: 5px;
		margin-left: 7px;
		vertical-align: middle;
	}
	.best-currency{
		background-color: #EFEDD5;
		margin-bottom: 0;
	}
	.best-currency thead th{
		border-bottom:1px solid #F8C98A;
	}
	.best-currency tbody td{
		border: 0px;
	}
	.best_conv{
		margin-left: 0px;
		padding-bottom: 20px;
		background-color: #F2F9FD;
		margin-left: 10px;
	}
	.best_conv p{
		color: #14509B;
		font-size: 15px;
		margin: 10px;
	}
	.best_conv input{
		width: 100%;
		margin-left: auto;
		margin-right: 5px;
	}
	.best_conv .container{
		margin-bottom: 7px;
	}
	.high{
		display: flex;
	}
	.cities{
		border-bottom:1px solid #F8C98A;
	}
	.city{
		padding: 10px;
		padding-right: 20px;
		color: #11a;
	}
	.city:hover{
		cursor: pointer;
		background-color: #F78F1E;
		color: white;
	}
	.bank-currency thead th{
		border-bottom: 1px solid #AFC6E0;
		background-color: #F2F9FD;
	}
	.bank-currency tr:hover{
		background-color: #e1e8e6;
		cursor: pointer;
	}
	.departments img{
		visibility: hidden;
	}
	.departments td:hover img{
		cursor: pointer;
		visibility: visible;
	}
	.back{
		font-size: 15px;
		color: blue;
	}
	.back:hover{
		cursor: pointer;
	}
	.items p:hover{
		color: #AFC6E0;
		cursor: pointer;
	}
</style>