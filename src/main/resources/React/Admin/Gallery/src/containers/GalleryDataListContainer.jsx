
import { connect } from 'react-redux';
import GalleryDataList from '../components/GalleryDataList'; 
import React, { Component } from 'react'
import { getContactsFn } from '../actions/actions'
import { LoadingHOC } from '../hoc/LoadingHOC'
 
const GalleryListWrapper = LoadingHOC('isloaded')(GalleryDataList)

class GalleryDataListContainer extends Component {

  state = {
    isLoading: true 
  }
     
  componentDidMount() {
    this.props.getGallery()
}

    render() {  
         let content={galleryList: this.props.galleryList,size:this.props.galleryList.length}
       let isloaded= this.props.isloaded;
         return (<GalleryListWrapper isloaded={isloaded} galleryList={content} page={this.props.page} />)
       
       
    }
}
const mapStateToProps = (state) => ({
  galleryList: getGallery(state),
  page:getPage(state),
  isloaded:getisloaded(state)


})
const getGallery = state => state.galleryReducer.galleryData;
const getPage = state => state.galleryReducer.page;
const getisloaded = state => state.galleryReducer.isloaded;
 
const mapDispatchToProps = (dispatch) => ({
  getGallery: () => dispatch(getContactsFn)
})
export default connect(mapStateToProps, mapDispatchToProps)(GalleryDataListContainer)