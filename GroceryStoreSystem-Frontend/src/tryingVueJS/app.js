
const app = Vue.createApp({
	template:  '<h1> {{storeName}}</h1>',
	data(){
		return{
			storeName: 'Hops Grocery',
			picture: 'https://i.pinimg.com/564x/d4/9c/6d/d49c6d145d811eb24e976567687576cf.jpg',
			
		}
	}
})

app.mount('#app')