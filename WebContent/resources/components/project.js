var id = document.getElementById("id").innerText;

Vue.component('project', {
   data:function () {
	    return {
	    	Project:''
	    }
	  },
	  methods:{
          submitForm: function(formAction) {
            document.getElementById(formAction).action = formAction+"?id="+id+"&type=p";
            document.getElementById(formAction).submit();
          },
	        getData: function(){

	            this.$http.get('getProjectData?id='+id).then(function(response){
	                this.Project = response.data;
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
        <div class="container bordered">
            <div class="row">
              <div class="col-sm-1">
                <img :src="Project.image" alt="project icon" style="margin-top:25px;">
                </div>
                <div class="col-sm-11">
                  <input type="text" name="project_name" class="normaltext element_input" style="font-size:25px;background-color:white;" :value="Project.name" readonly>
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
              <form id="setVersion" method="post" >
              <input v-on:change="submitForm('setVersion')"  type="text"  name="version" class="normaltext element_input" :value="Project.version" >
              </form>
            </div>
            </div>


            <div class="row">
              <div class="col-sm-2">
                <p class="label">Description:</p>
              </div>
                      <div class="col-12" >
                      <form id="setDescription" method="post" >
                        <textarea name="description" v-on:change="submitForm('setDescription')" class="normaltext element_input" cols="150" rows="15">
                        {{Project.description}}
                        </textarea>
                        </form>
                      </div>
              </div>



            </div>


	  		</div>


          <link rel="stylesheet" type="text/css" href="resources/css/project.css">
        </div>


  `

})
