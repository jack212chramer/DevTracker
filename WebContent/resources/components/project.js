Vue.component('project', {
   data:function () {
	    return {
	    	projectData:''
	    }
	  },
	  methods:{
	        getData: function(){
	            this.$http.get('getProjectData').then(function(response){
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
	  <div class="project">
        <div class="container">
            <div class="row">
              <div class="col-sm-1">
                <img :src="Project.image" alt="project icon">
                </div>
                <div class="col-sm-11">
                  <input type="text" name="project_name" class="normaltext element_input" style="font-size:25px;background-color:white;" value="{{Project.name}}" readonly>
                </div>
                <div class="row">

                  <div class="col-sm-2">
                    <p class="label">Created by:</p>
                  </div>
                <div class="col-sm-2">
                  <input type="text" id="createdBy" name="createdBy" class=" normaltext element_input" value="user" readonly>
                </div>

                <div class="col-sm-2">
                  <p class="label">Created:</p>
                </div>
              <div class="col-sm-2">
                <input type="text"  name="creationDate" class="normaltext element_input" value="12.11.2018" readonly>
              </div>

              <div class="col-sm-2">
                <p class="label">Version:</p>
              </div>
            <div class="col-sm-2">
              <input type="text"  name="version" class="normaltext element_input" value="{{version}}" >
            </div>
            </div>


            <div class="row">
              <div class="col-sm-2">
                <p class="label">Description:</p>
              </div>
                      <div class="col-12" >
                        <textarea class="normaltext element_input" cols="150" rows="15">
                        {{Project.description}}
                        </textarea>
                      </div>
              </div>



            </div>


	  		</div>
            <style>

              .label{
                margin: 25px;
              }
              img{
                margin-top:25px;
                height:50px;
              }
            </style>
        </div>
        

  `

})
