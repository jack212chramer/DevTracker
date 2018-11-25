Vue.component('pinned', {
   data:function () {
	    return {
	    	pinnedData:''
	    }
	  },
	  methods:{
	        getData: function(){
	            this.$http.get('getPinnedElements').then(function(response){
	                this.pinnedData = response.data;
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

        <div class="container pinned">
        <div class="row pinnedBackground normaltext" style="border-radius:5px;">
          <div class="col-sm-12" >
          <h3 class="pinnedWhite">Pinned concepts:</h3>
        </div>
            <div class="col-sm-12 a_pinned" v-for="pinned in pinnedData">
            <a :href="'element?type='+pinned.element_type+'&id'+pinned.element_id" >{{pinned.name}}</a>
            <a>{{pinned.assigned}}</a>
              <a>{{pinned.status}}</a>
            </div>
        </div>

        <link rel="stylesheet" type="text/css" href="css/pinnedElements.css">
    </div>
  `

})
