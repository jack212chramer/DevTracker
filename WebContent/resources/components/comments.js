Vue.component('comments', {
   data:function () {
	    return {
	    	comments:''
	    }
	  },
	  methods:{
	        getData: function(){
	        	var type = document.getElementById("type").innerText;
	        	var id = document.getElementById("id").innerText;

	            this.$http.get('getComments?type='+type+'&id='+id).then(function(response){
	                this.comments = response.data;
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
    <div class="container bordered">
        <div class="row">
            <div class="col-sm-8" v-for="comment in comments">
                <div class="panel panel-white post panel-shadow">
                    <div class="post-heading">
                        <div class="pull-left image">
                            <img src="http://bootdey.com/img/Content/user_1.jpg" class="img-circle avatar" alt="user profile image">
                        </div>
                        <div class="pull-left meta">
                            <div class="title h5">
                                <a href="#"><b>{{comment.username}}</b></a>
                                made a post.
                            </div>
                        </div>
                    </div>
                    <div class="post-description">
                        <p>{{comment.comment}}</p>
                        <div class="stats">
                            <a href="#" class="btn btn-default stat-item">
                                <i class="fa fa-thumbs-up icon"></i>0
                            </a>
                            <a href="#" class="btn btn-default stat-item">
                                <i class="fa fa-thumbs-down icon"></i>0
                            </a>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <link rel="stylesheet" type="text/css" href="resources/css/comment.css">
    </div>
  `

})
