var id = document.getElementById("id").innerText;
var type = document.getElementById("type").innerText;

Vue.component('panel', {
   data:function () {
	    return {
	    	elementData:''
	    }
	  },
	  methods:{
          submitForm: function(formAction) {
            //document.getElementById(formAction).action = formAction+"?id="+id;
            document.getElementById(formAction).submit();
          },
	        getData: function(){

	            this.$http.get('getElementData?id='+id+"&type="+type).then(function(response){
	                this.elementData = response.data;
	            }, function(error){
	                console.log(error.statusText);
	            });
	        },
          doMath: function (index) {
            return index+1
          }
	    },
	    mounted: function () {
	        this.getData();
	    },
  template:
	  `
	  <div class="panel">
	  <iframe width="0" height="0" border="0" name="dummyframe" id="dummyframe"></iframe>

        <div class="container bordered">
            <div class="row">
              <div class="col-sm-1" v-if="elementData.element_type=='p'">
                <img :src="elementData.image" alt="element icon" style="margin-top:25px;">
                </div>
                <div class="col-sm-11">
                  <input type="text" name="element_name" class="normaltext element_input" style="font-size:25px;background-color:white;" :value="elementData.name" readonly>
                </div>
                <div class="row">

                  <div class="col-sm-2">
                    <p class="label">Type:</p>
                  </div>

                  <div class="col-sm-2">

                  <input v-if="elementData.element_type === 'p'" type="text" class=" normaltext element_input" value="Project" readonly>
                <input v-if="elementData.element_type === 'c'" type="text" class=" normaltext element_input" value="Concept" readonly>
                <input v-if="elementData.element_type === 't'" type="text" class=" normaltext element_input" value="Task" readonly>
                <input v-if="elementData.element_type === 's'" type="text" class=" normaltext element_input" value="Subtask" readonly>

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
              <form id="setVersion" method="get"  target="dummyframe" action="setVersion">
              <input v-on:change="submitForm('setVersion')"  type="text"  name="version" class="normaltext element_input" :value="elementData.version" >
              <input name="type" :value="elementData.element_type" hidden>
              <input name="id" :value="elementData.id" hidden>
              </form>
            </div>
            </div>



            <div class="row" v-if="elementData.element_type !='p'">

              <div class="col-sm-2">
                <p class="label">Priority:</p>
              </div>
            <div class="col-sm-2">

              <form id="setPriority" action="setPriority" method="get" target="dummyframe">
              <select class="form-control" style="margin-top:22px;" name="priority" v-on:change="submitForm('setPriority')" :value=elementData.priority>
                <option style="color:DarkRed;" value=5>Blocker</option>
                <option style="color:red;" value=4>Very High</option>
                <option style="color:orange;" value=3>High</option>
                <option value=2>Medium</option>
                <option style="color:green;" value=1>Low</option>
                <option style="color:DarkGreen;" value=0>Very Low</option>
              </select>
              <input name="type" :value="elementData.element_type" hidden>
              <input name="id" :value="elementData.id" hidden>
            </form>
            </div>

            <div class="col-sm-2">
              <p class="label">Status:</p>
            </div>
          <div class="col-sm-2">
              <form id="setStatus" action="setStatus" method="get" target="dummyframe">
              <select class="form-control" style="margin-top:22px;" name="status" v-on:change="submitForm('setStatus')" >
                <option v-for="(status,index) in elementData.workflow" v-bind:style="{ color: 'hsl('+index*90/elementData.workflow.length+', 100%, 25%)'}" :value="doMath(index)" :selected="elementData.status==index">{{status}}</option>
              </select>
              <input name="type" :value="elementData.element_type" hidden>
              <input name="id" :value="elementData.id" hidden>
            </form>
          </div>

          <div class="col-sm-2">
            <p class="label">Assigned to:</p>
          </div>
        <div class="col-sm-2">
          <form id="setVersion" method="post" target="dummyframe">
          <input type="text"  class="normaltext element_input" value="" >
          </form>
        </div>
        </div>




            <div class="row">
              <div class="col-sm-2">
                <p class="label">Description:</p>
              </div>
                      <div class="col-12" >
                      <form action="setDescription" id="setDescription" method="get" target="dummyframe">
                        <textarea name="description" v-on:change="submitForm('setDescription')" class="normaltext element_input" cols="150" rows="15">
                        {{elementData.description}}
                        </textarea>
                        <input name="type" :value="elementData.element_type" hidden>
                        <input name="id" :value="elementData.id" hidden>
                        </form>
                      </div>
              </div>



            </div>


	  		</div>


          <link rel="stylesheet" type="text/css" href="resources/css/project.css">
        </div>


  `

})
