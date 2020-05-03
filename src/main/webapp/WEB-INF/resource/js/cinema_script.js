var domain="";
							var tag="";
							var link="";
								
								function recommedMovies(content)
								{
									for (i in content) {
										
										populaterecommended(content[i]);
									}
								}
								function populateFeaturePost(object,html)
								{ 
									html+='<div class="feature-post relative"> <div class="feature-img relative"> <div class="overlay overlay-bg"></div>';
									html+='<img class="img-fluid" src="'+domain+"/pic/"+object.thumbnail2+'"  alt="">';
									html+='</div> 	<div class="details"> <ul class="tags">';
									html+='<li><a href="'+domain+'/cinema/Popular-Posts">Popular Posts</a></li>';
									html+='</ul> <a href="image-post.html"> <h3>'+object.movieName +'</h3> </a>';
									html += '<ul class="meta"> <li><a href="#">  ';
									for (L in object.movieTypeVos) {
										html += '<a href="cinema/movietype_'+object.movieTypeVos[L].link+'">'+object.movieTypeVos[L].tag+"</a>,"
									}
										
									html += ' ('+object.certificate+') </a></li>';
 
									html+='<li><a href="#">Rating <div class="ratings"> <div class="empty-stars"></div>';
									html+='<div class="full-stars" style="width: '+object.movieRate+'%"></div> </div></li>';
									html+='<li><a href="#"><span class="lnr lnr-calendar-full"></span>'+object.releaseDate+'</a></li>';
									html+='</ul> </div> </div>';
									return html; 
								 }
								function populaterecommended(obj)
								{
									var i=1;
									var html="";
									for (k in obj) {

											if(i==1)
											{
												html=populateFeaturePost(obj[k],html);
											}
											else 
											{
												if(i%2==0)
												{
													html+='<div class="row mt-20 medium-gutters">';
													html+=populateSubPost(obj[k]);
												}
												else
												{
													html+=populateSubPost(obj[k]);
													html+="</div>";
												}

											}
									 i++;
									}
									$('#content-popular-post').html(html);

								}
								function populateSubPost(object)
								{
									var html="";
									  html += '<div class="col-lg-6 single-popular-post"> <div class="feature-img-wrap relative"> <div class="feature-img relative"><div class="overlay overlay-bg"></div>';
									html += '<img class="img-fluid" src="'+domain+"/pic/"+object.thumbnail+'" alt="">';
									html += '</div> <ul class="tags"> <li><a href="'+domain+'/cinema/'+link+'">'+tag+'</a></li> </ul> </div>';
									html += '<div class="details"> <a href="'+domain+"/cinema/"+object.movieCode+'"> <h4>'
											+ object.movieName
											+ '</h4> </a>';
									html += '<ul class="meta"> <li><a href="#">  ';
									for (L in object.movieTypeVos) {
										html += '<a href="cinema/movietype_'+object.movieTypeVos[L].link+'">'+object.movieTypeVos[L].tag+"</a>,"
									}
										
									html += ' ('+object.certificate+') </a></li>';
									html += '<li><a href="#">Rating <div class="ratings"> <div class="empty-stars"></div> <div class="full-stars"';
									html += ' style="width:'+object.movieRate+'%"></div> </div></a></li> <li><a href="#"><span 	class="lnr lnr-calendar-full"></span>';
									html += object.releaseDate
											+ '</a></li>';
									html += ' </ul> </div> </div>';
									return html;
								}
								function updateMovies(content,id)
								{
									html="";
									var isActive=false;
									for (i in content) {
										if(i=='mal')
										{isActive=true;
										}
										else
											{isActive=false;}
										populate(id+"-"+i,content[i],isActive);
									}
									$('#nav-tabContent-'+id).html(html);
										 
								}
								function populate(id,content,isActive)
								{
									
								  var n = 1;

									html += '<div class="tab-pane fade show';
									if(isActive)
									{ html += ' active'; }
 									 html += '" id="nav-'+id+'" role="tabpanel" aria-labelledby="nav-'+id+'-tab">';
 									for (k in content) {
										if (n % 2 == 1) {
											html += '<div class="row mt-20 medium-gutters">';
										}

										html += '<div class="col-lg-6 single-popular-post"> <div class="feature-img-wrap relative"> <div class="feature-img relative"><div class="overlay overlay-bg"></div>';
										html += '<img class="img-fluid" src="'+domain+"/pic/"+content[k].thumbnail+'" alt="">';
										html += '</div> <ul class="tags"> <li><a href="'+domain+'/cinema/'+link+'">'+tag+'</a></li> </ul> </div>';
										html += '<div class="details"> <a href="'+domain+"/cinema/"+content[k].movieCode+'"> <h4>'
												+ content[k].movieName
												+ '</h4> </a>';
										html += '<ul class="meta"> <li><a href="#">  ';
										for (L in content[k].movieTypeVos) {
											html += '<a href="cinema/movietype_'+content[k].movieTypeVos[L].link+'">'+content[k].movieTypeVos[L].tag+"</a>,"
										}
											
										html += ' ('+content[k].certificate+') </a></li>';
										html += '<li><a href="#">Rating <div class="ratings"> <div class="empty-stars"></div> <div class="full-stars"';
										html += ' style="width:'+content[k].movieRate+'%"></div> </div></a></li> <li><a href="#"><span 	class="lnr lnr-calendar-full"></span>';
										html += content[k].releaseDate
												+ '</a></li>';
										html += ' </ul> </div> </div>';

										if (n % 2 == 0) {
											html += '</div>';
										}
										n++;
									}
									
									html += '</div>';

								}