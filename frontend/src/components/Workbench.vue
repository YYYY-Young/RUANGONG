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
							<li id="link1">
								<a href="www.baidu.com"><span>文档1</span></a>
							</li>
							<li id="link2">
								<a href="www.baidu.com"><span>文档2</span></a>
							</li>
							<li id="link3">
								<a href="www.baidu.com"><span>文档3</span></a>
							</li>
						</ul>

                        <h2>文档列表</h2>
						
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
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

@import "../assets/css/Workbench.css"

</style>
