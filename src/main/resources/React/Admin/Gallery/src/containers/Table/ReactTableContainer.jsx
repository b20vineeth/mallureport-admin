import { connect } from 'react-redux';
import ReactTable from '../../components/Table/ReactTable';
import React, { Component } from 'react'
import { getRenderTable } from '../../actions/actions'

const mapStateToProps = (state) => {
  return {
    datasss: getData(state),
    fieldData: getfieldData(state),


  };
};

const getData = state => state.dataListReducer.datasss;
const getfieldData = state => state.dataListReducer.fieldData;
const mapDispatchToProps = (dispatch) => {

  return {

    dataLoad: (data, columns, page, isEmptyError) => {
      dispatch({ type: 'DATA_SAVE', data: data.galleryList, columns: columns, page: page, isEmptyError: isEmptyError });
      dispatch(getRenderTable);
    }
  }
}
export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ReactTable); 