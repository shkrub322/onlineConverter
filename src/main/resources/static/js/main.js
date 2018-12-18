var reqReg = Vue.resource("/regions");
var reqCity = Vue.resource("/cities{/id}");

var reqBankCity = Vue.resource("/banks/city{/id}");
var reqBankRegion = Vue.resource("/banks/region{/id}");
var reqBanks = Vue.resource("/banks");

Vue.component("department-row", {
    props: ['department', 'rates'],
    data: function(){
        return{
            rate: null
        }
    },
    template:
        '<tr><td>{{department.name}} <br><i>{{department.address}}</i></td>' +
        '<td v-for="rate in rates">{{rate.value}}</td>' +
        '</tr>',
    created: function () {
        this.rates = [];
        this.$http.get("/rates", {params: {department: this.department.id}}).then(result => result.json()
            .then(data => data.forEach(rate => this.rates.push(rate))));
    }
});

Vue.component("bank-row", {
    props: ['bank', 'cityId', 'regionId', 'departments', "rates"],
    data: function() {
        return{
            test: [{val: 1}, {val: 2}, {val: 3}, {val: 4}],
            flag: false,
            department: null
        }
    },
    template:
        '<div>' +
        '<tr>' +
        '<td style="cursor: pointer" @click="show">{{bank.name}}</td>' +
        '</tr>' +
        '<tr v-if="flag">' +
        '<div class="scroll">' +
        '<table class="table table-hover table-condensed">' +
        '<thead class="thead-dark">' +
        '<tr>' +
        '<th>Отделение <br> {{bank.name}}</th>' +
        '<th>USD <br>(покупка)</th>' +
        '<th>USD <br>(продажа)</th>' +
        '<th>EUR <br>(покупка)</th>' +
        '<th>EUR <br>(продажа)</th>' +
        '<th>RUB <br>(покупка)</th>' +
        '<th>RUB <br>(продажа)</th>' +
        '<th>EUR/USD <br>(покупка)</th>' +
        '<th>EUR/USD <br>(продажа)</th>' +
        '</tr>' +
        '</thead>' +
        '<tbody>' +
        '<department-row :rates="rates" v-for="department in departments" ' +
        ':key="department.id" :department="department" />' +
        '</tbody>' +
        '</table>' +
        '</div>' +

        '</tr>' +
        '</div>',
    watch: {
        flag: function (oldVal, newVal) {
            arr = [1, 1, 1, 1, 1, 1, 1];
            this.departments = [];
            if (this.cityId){
                this.$http.get("/departments/city", {params: {bank: this.bank.id, city: this.cityId}}).then(result => result.json()
                    .then(data => data.forEach(department => this.departments.push(department))));
            }
            else if(!this.cityId && this.regionId){
                this.$http.get("/departments/region", {params: {bank: this.bank.id, region: this.regionId}}).then(result => result.json()
                    .then(data => data.forEach(department => this.departments.push(department))));
            } else {
                this.$http.get("/departments", {params: {bank: this.bank.id}}).then(result => result.json()
                    .then(data => data.forEach(department => this.departments.push(department))));
            }
        }
    },
    methods: {
        show: function () {
            this.flag = !this.flag;
        }
    }
});

Vue.component("option-region", {
    props: ['region'],
    template: '<option v-bind:value="region.id">{{region.name}}</option>'
});

Vue.component("option-city", {
    props: ['city'],
    template: '<option v-bind:value="city.id">{{city.name}}</option>'
});

Vue.component("select-regions", {
    props: ['regions', 'cities', 'banks', 'departments', 'rates'],
    data: function () {
        return{
            region: null,
            city: null,
            bank: null,
            regionId: false,
            cityId: false
        };
    },
    template:
        '<div class="container">' +
        '<div class="row">' +
        '<main class="col-12">' +
        '<select class="form-control form-control-lg" v-model="regionId">' +
        '<option selected value="">All</option>' +
        '<option-region v-for="region in regions" :key="region.id" :region="region"/>' +
        '</select>' +
        '<select class="form-control form-control-lg" v-if="regionId" v-model="cityId">' +
        '<option selected value="">All</option> ' +
        '<option-city v-for="city in cities" :key="city.id" :city="city"/>' +
        '</select>' + '<br>' +
        '<table class="table table-hover">' +
        '<thead>' +
        '<tr>' +
        '<th>Банк</th>' +
        '</tr>' +
        '</thead>' +
        '<tbody>' +
        '<bank-row :departments="departments" :rates="rates" :cityId="cityId" ' +
        ':regionId="regionId" v-for="bank in banks" :key="bank.id" :bank="bank"/>' +
        '</tbody>' +
        '</table>' +
        '</main> ' +
        '</div>' +
        '</div>',
    created: function () {
        reqReg.get().then(result => result.json().then(data => data.forEach(region => this.regions.push(region))));
        reqBanks.get().then(result => result.json().then(data => data.forEach(bank => this.banks.push(bank))));
    },
    watch: {
        regionId: function (newVal, oldVal) {
            this.cityId = false;
            this.cities = [];
            this.banks = [];
            if (this.regionId){
                reqCity.get({id: this.regionId}).then(result => result.json().then(data => data.forEach(city => this.cities.push(city))));
                reqBankRegion.get({id: this.regionId}).then(result => result.json().then(data => data.forEach(bank => this.banks.push(bank))));
            } else {
                reqBanks.get().then(result => result.json().then(data => data.forEach(bank => this.banks.push(bank))));
            }
        },
        cityId: function (newVal, oldVal) {
            if(this.cityId){
                this.banks = [];
                if (this.cityId){
                    reqBankCity.get({id: this.cityId}).then(result => result.json().then(data => data.forEach(bank => this.banks.push(bank))));
                }
                else {
                    reqBankRegion.get({id: this.regionId}).then(result => result.json().then(data => data.forEach(bank => this.banks.push(bank))));
                }
            }
        }
    }
});

Vue.component('rate-table',{
    props: ['regions', 'cities', 'vals'],
    data: function(){
        return{
            val: null
        }
    },
    template:
        '<table class="table table-hover">' +
        '<thead class="thead-dark">' +
        '<tr>' +
        '<th>Область</th>' +
        '<th>Город</th>' +
        '<th>Банк</th>' +
        '<th>Отделение</th>' +
        '<th>Адрес</th>' +
        '<th>Курс</th>' +
        '</tr>' +
        '</thead>' +
        '<tbody>' +
        '<tr v-for="val in vals">' +
        '<td>{{val.department.city.region.name}}</td>' +
        '<td>{{val.department.city.name}}</td>' +
        '<td>{{val.department.bank.name}}</td>' +
        '<td>{{val.department.name}}</td>' +
        '<td>{{val.department.address}}</td>' +
        '<td>{{val.value}}</td>' +
        '</tr>' +
        '</tbody>' +
        '</table>'
});

Vue.component("rate-form", {
    props: ['vals', 'regions', 'cities'],
    data: function(){
        return{
            button: 'Show Table',
            show: false,
            rates: [],
            rate: null,
            from: '',
            to: '',
            value: 0,
            val: null,
            profit: 0,
            region: null,
            city: null,
            regionId: false,
            cityId: false
        }
    },
    created: function () {
        reqReg.get().then(result => result.json().then(data => data.forEach(region => this.regions.push(region))));
    },
    template:
        '<div class="container">' +
        '<div class="row">' +
        '<main class="col-12">' +
        '<select v-model="from" class="form-control col-1">' +
        '<option>USD</option>' +
        '<option>EUR</option>' +
        '<option>BYN</option>' +
        '<option>RUB</option>' +
        '</select>' +
        '<input v-model="value" style="margin-left: 15px"/><br>' +
        '<select class="form-control col-2" v-model="regionId" style="margin-left: 50px">' +
        '<option selected value="">All</option>' +
        '<option-region v-for="region in regions" :key="region.id" :region="region"/>' +
        '</select>' +
        '<select class="form-control col-2" v-if="regionId" v-model="cityId" style="margin-left: 20px">' +
        '<option selected value="">All</option> ' +
        '<option-city v-for="city in cities" :key="city.id" :city="city"/>' +
        '</select>' +
        '</div>' +
        '<div class="form-row" style="margin-top: 20px">' +
        '<select v-model="to" class="form-control col-1">' +
        '<option v-for="rate in rates">{{rate}}</option>' +
        '</select>' +
        '<p style="margin-left: 50px" class="col-6 h3">{{Math.round(value*profit*100) / 100}}</p>' +
        '</div>' +
        '<div class="form-row" style="margin-top: 15px; margin-bottom: 50px">' +
        '<button @click="flag" class="btn btn-light">{{button}}</button>' +
        '</div> ' +
        '<rate-table :vals="vals" v-if="show"></rate-table>' +
        '<div class="form-row">' +
        '</main> ' +
        '</div>' +
        '</div>',
    watch: {
        from: function () {
            this.to = '';
            switch (this.from) {
                case 'USD': this.rates = ['EUR', 'BYN'];
                    break;
                case 'EUR': this.rates = ['USD', 'BYN'];
                    break;
                case 'BYN': this.rates = ['USD', 'EUR', 'RUB'];
                    break;
                case 'RUB': this.rates = ['BYN'];
                    break;
            }
        },
        to: function () {
            this.vals=[];
            if (this.to && this.cityId){
                this.$http.get('rates/convertCity', {params:{from: this.from, to: this.to, city: this.cityId}})
                    .then(result=>result.json().then(data=>data.forEach(val=> {
                        this.vals.push(val);
                        this.profit = val.value
                        })));
            } else if (this.to && this.regionId){
                this.$http.get('rates/convertRegion', {params:{from: this.from, to: this.to, region: this.regionId}})
                    .then(result=>result.json().then(data=>data.forEach(val=> {
                        this.vals.push(val);
                        this.profit = val.value;
                        })));
            } else if (this.to){
                this.$http.get('/rates/convert', {params:{from: this.from, to: this.to}})
                    .then(result=>result.json().then(data=>data.forEach(val=>{
                        this.vals.push(val);
                        this.profit = val.value
                        })));
            }
        },
        regionId: function (newVal, oldVal) {
            this.cityId = false;
            this.cities = [];
            this.banks = [];
            this.vals = [];
            if (this.regionId){
                reqCity.get({id: this.regionId}).then(result => result.json().then(data => data.forEach(city => this.cities.push(city))));
            } else{
                this.$http.get('/rates/convert', {params:{from: this.from, to: this.to}})
                    .then(result=>result.json().then(data=>data.forEach(val=>{
                        this.vals.push(val);
                        this.profit = val.value
                    })));
            }
            if (this.to && this.regionId){
                this.$http.get('rates/convertRegion', {params:{from: this.from, to: this.to, region: this.regionId}})
                    .then(result=>result.json().then(data=>data.forEach(val=> {
                        this.vals.push(val);
                        this.profit = val.value
                        })));
            }
        },
        cityId: function (newVal, oldVal) {
            this.vals = [];
            if(this.cityId && this.to){
                this.$http.get('rates/convertCity', {params:{from: this.from, to: this.to, city: this.cityId}})
                    .then(result=>result.json().then(data=>data.forEach(val=> {
                        this.vals.push(val);
                        this.profit = val.value
                        })));
            }
        }
    },
    methods: {
        flag: function () {
            this.show = !this.show;
            if (this.show){
                this.button = 'Hidden Table';
            } else {
                this.button = 'Show Table';
            }
        }
    }
});

const Home = {
    data: function () {
        return{
            regions: [],
            cities: [],
            banks: [],
            departments: [],
            rates: []
        }
    },
    template: '<div class="container"><select-regions :regions="regions" :cities="cities" :banks="banks" ' +
        ' :departments="departments" :rates="rates"/></div>'
};

const Converter = {
    data: function() {
        return{
            vals: [],
            regions: [],
            cities: [],
        }
    },
    template: '<div class="container"><rate-form :vals="vals" :regions="regions" :cities="cities"/></div>'
};

const routes = [
    {path: '/', component: Home },
    {path: '/converter', component: Converter}
];

const router = new VueRouter({
    mode: 'history',

    routes: routes
});

Vue.component('nav-bar',{
    template:
        '<header>' +
        '<nav class="navbar navbar-dark bg-dark">\n' +
        '        <h1 style="color: white">Online Converter</h1>\n' +
        '        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">\n' +
        '            <span class="navbar-toggler-icon"></span>\n' +
        '        </button>\n' +
        '        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">\n' +
        '            <div class="navbar-nav">\n' +
        '                <router-link style="text-decoration: none; outline: none" to="/" exact>\n' +
        '                    <a style="cursor: pointer" class="nav-item nav-link">Home</a>\n' +
        '                </router-link>\n' +
        '                <router-link style="text-decoration: none; outline: none" to="/converter">\n' +
        '                    <a style="cursor: pointer" class="nav-item nav-link">Converter</a>\n' +
        '                </router-link>\n' +
        '            </div>\n' +
        '        </div>\n' +
        '</nav>' +
        '</header>'
});

new Vue({
    el: '#app',
    router: router,
    template:
        '<div>' +
        '<nav-bar></nav-bar>' +
        '<section><router-view></router-view></section>' +
        '</div>'
});








