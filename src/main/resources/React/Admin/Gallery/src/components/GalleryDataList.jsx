
import React, { Component } from 'react'
import ReactTableContainer from '../containers/Table/ReactTableContainer'
const columns = [
    {
      Header: 'Title',
      accessor: 'title',
      headerStyle: 'col-xs-3',
      style: 'col-xs-3',
    },
    {
      Header: 'Short Desc',
      accessor: 'shortDesc',
      headerStyle: 'col-xs-3',
      style: 'col-xs-3',
    },
    {
        Header: 'createdDate',
        accessor: 'createdDate',
        headerStyle: 'col-xs-2',
        style: 'col-xs-2',
      },
      {
        Header: 'description',
        accessor: 'description',
        headerStyle: 'col-xs-3',
        style: 'col-xs-3',
      } ,
      {
        Header: '',
        accessor: 'tool',
        headerStyle: 'col-xs-1',
        style: 'col-xs-1',
        editTool:['edit','delete','deactive','view']
      } 
    
  ];
export default class GalleryDataList extends Component {
     
    state = {
        isLoading: true ,
        galleryList:this.props.galleryList
      }
      
    render() { 
     
        let page=this.props.page; 
       
        return (
          <ReactTableContainer content={this.props.galleryList} columns={columns} isEmptyError="No data Found"  page={page}></ReactTableContainer>


        )
    }
}
