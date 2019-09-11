import React, { Component } from 'react'  
import {Field} from 'redux-form';

export default class GalleryFilter extends Component {
    constructor(props) {
        super(props);
        this.state = {}
    } 
   
    render() {
        return (
 <form>
             



               <div className="row">
                    <div className="col-sm-6 col-md-3">
                            <div className="form-group">
                                 <label for="input-text-1">Title</label>
                         
                                 <Field type="text"  className="form-control"  
                                 component = "input"   placeholder = "Title"
                                 name= "title"  />
                            </div>
                    </div>
                    <div className="col-sm-6 col-md-3">

                            <div className="form-group">
                                 <label for="input-text-1">Pic Url</label>
                         
                                 <Field type="text"  className="form-control"  
                                 component = "input"   placeholder = "Pic Url"    
                                 name= "picUrl" />
                            </div>
                    </div>
                    <div className="col-sm-6 col-md-3">

                            <div className="form-group">
                                 <label for="input-text-1">Gallery Url</label>
                         
                                 <Field type="text"  className="form-control"  
                                 component = "input"   placeholder = "Gallery Url"
                                 name= "galleryUrl"  />
                            </div>
                    </div>
                    <div className="col-sm-6 col-md-3">
                            <div className="form-group">
                                 <label for="input-text-1">Thumb Url</label>
                         
                                 <Field type="text"  className="form-control"  
                                 component = "input"   placeholder = "Thumb Url"             
                                 name= "thumbUrl" />
                            </div>
                    </div>
                </div>


                <div className="row">
                    <div className="col-sm-4">
                             <div className="form-group">
                                 <label for="input-text-1">Thumb Url</label>
                         
                                 <Field type="text"  className="form-control"  
                                 component = "textarea"   placeholder = "Thumb Url"             
                                 name= "thumbUrl" />
                            </div>


                    </div>
                    <div className="col-sm-4">

                            <div className="form-group">
                                 <label for="input-text-1">Thumb Url</label>
                         
                                 <Field type="text"  className="form-control"  
                                 component = "textarea"   placeholder = "Thumb Url"             
                                 name= "thumbUrl" />
                            </div>



                    </div>
                    <div className="col-sm-4">

                            <div className="form-group">
                                 <label for="input-text-1">Thumb Url</label>
                         
                                 <Field type="text"  className="form-control"  
                                 component = "textarea"   placeholder = "Thumb Url"             
                                 name= "thumbUrl" />
                            </div>


                    </div>
                </div>
                <div className="row">
                     <div className="col-sm-12">
                            <button type="button" className="btn btn-primary">Save</button>
                     </div>
                </div>

 
   
</form> 
          
            )
      }
  }
  