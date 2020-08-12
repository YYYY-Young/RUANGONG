<template>
  <div class="border">
		<div id="bg">
			background
		</div>
		<div class="page">
			<div class="sidebar">
                 <!-- 
				<a href="www.baidu.com" id="logo"><img src="../assets/images/logo.png" alt="logo"></a>
                 -->
                 
                <ul>
					<li class="selected">
						<a href="www.baidu.com">工作台</a>
					</li>
					<li>
						<a href="www.baidu.com">收件箱</a>
					</li>
					<li>
						<a href="www.baidu.com">我的桌面</a>
					</li>
					<li>
						<a href="www.baidu.com">团队空间</a>
					</li>
					<li>
						<a href="www.baidu.com">回收站</a>
					</li>
					<li>
						<a href="www.baidu.com">帮助中心</a>
					</li>
				</ul>
				
				
			</div>
			<div class="body">
				<div>
					<h2>我的文档</h2>
						<p>
						最近浏览
						</p> 
						<div>
							<ul>
								<li id="link1" style="">
									<img src="../assets/images/design.png"  style="width:95%;height:80%" @click="createarticle()">
									<span>创建新文档</span>
								</li>
								<li id="link2">
									<img src="../assets/images/design.png" style="width:95%;height:80%">
									<span>文档1</span>
								</li>
								<li id="link3">
									<img src="../assets/images/design.png" style="width:95%;height:80%">
									<span>文档2</span>
								</li>
								<li id="link4">
									<img src="../assets/images/more-design.png" style="width:95%;height:80%" @click="morearticle()">
									<span>更多</span>
								</li>
							</ul>
							
						</div>                      					
				</div>
			</div>
		</div>
	</div>
</template>

<script>
export default {
  name: 'Workbench',
  props: {
    msg: String
  },
  methods: {
		createarticle(){
			let articleUrl = this.$router.resolve({
				name: 'ArticleEditor', 

			})
			window.open(articleUrl.href,'_blank')
		},
		morearticle(){
			let articleUrl = this.$router.resolve({
				name: 'Articles', 

			})
			window.open(articleUrl.href,'_blank')
		}
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style >
.card{
width: auto;
height: 10px;
}
.image{
width: 100%;
display: block;
}
.la{
width: auto;
height: auto;
}
@import "../assets/css/Workbench.css"

</style>
