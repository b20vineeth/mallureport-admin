import React, { Component } from 'react' 
import GalleryFilterContainer from '../containers/GalleryFilterContainer'
import GalleryDataListContainer from '../containers/GalleryDataListContainer'
import HeaderPanel from '../components/HeaderPanel'
import FooterPanel from '../components/FooterPanel'

export default class ContactApp extends Component {


  render() {
   
    return (
      <div>
          <HeaderPanel/>
          <div className="container pad">
            <GalleryFilterContainer />
            <GalleryDataListContainer />
          </div>
          <FooterPanel/>
         
       
      </div>
    )
  }
}

