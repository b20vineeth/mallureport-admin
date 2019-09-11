import { connect } from 'react-redux';
import GalleryFilterPanel from '../components/GalleryFilter'; 
import { reduxForm } from 'redux-form';
import React, { Component } from 'react' 
class GalleryFilter extends Component {
}
const mapStateToProps = (state,ownProps) => ({
  })


const mapDispatchToProps = (dispatch) => ({
  
}) 
GalleryFilter = connect(
  mapStateToProps,
  mapDispatchToProps
)(GalleryFilterPanel);
export default reduxForm({  form: 'GalleryForm'})(GalleryFilter);