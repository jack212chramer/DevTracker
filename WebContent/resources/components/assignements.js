	Vue.component('assignements', {
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
			        }
			    },
			    mounted: function () {
			        this.getData();
			    },
  template: 
	  `
<div style="margin-top: 50px;" class="container">
    <div class="row">
        <div class="col-md-6">
          <h3>Your assignements:</h3>
      <div class="card" style="background-color:green;">
          <div class="card-body">
            <div v-for="ass in assignementsData" class="card assign">
            <a :href="'element?id='+ ass.id+'&type='+ass.element_type">
            <div class="card-body" >{{ass.name}}</div>
            </a>
          </div>
          </div>
        </div>
        </div>
    </div>
</div>
   `
})