Vue.component('navigation', {
   data:function () {
	    return {
	    	navbarData:''
	    }
	  },
	  methods:{
	        getData: function(){
	            this.$http.get('getNavbarData').then(function(response){
	                this.navbarData = response.data;
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
  <div class="navbar">
  <a class="active" href="/DevTracker"><i class="fa fa-fw fa-home"></i> Dashboard</a> 
  <a href="#" data-toggle="modal" data-target="#myModal"><i  class="fas fa-project-diagram"></i> Projects</a> 
  <a href="issues"><i  class="fas fa-bug"></i> Issues</a> 
  <a href="concepts"><i class="far fa-clipboard"></i></i> Concepts</a>
  <a href="sprints"><i class="fas fa-running"></i> Sprints</a>
  <a href="profile"><i class="fa fa-fw fa-user"></i> {{navbarData.username}}</a>
  <div><input type="search" name="search" placeholder="Search...">
  <!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        
        <h4 class="modal-title">Your projects:</h4>
      </div>
      <div v-for="project in navbarData.projects" class="modal-body">
      	<img style="height: 50px;float: left;margin-right: 25px;" :src="project.image">
        <a :href="'element?type=p&id='+ project.id" style="color: black;">{{project.name}}</a>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>
  </div>
  </div>
  `

})
