import { connect } from 'react-redux';
import ReactTableRow from '../../components/Table/ReactTableRow';
import React, { Component } from 'react' 

const mapStateToProps = (state) => {
  return {
    columnList: getColumnList(state),
    fieldData: getfieldData(state) ,
    isEmptyError:getEmptyError(state)
  };
};
 
const getColumnList = state => state.dataListReducer.columns;
const getfieldData = state => state.dataListReducer.fieldData;
const getEmptyError = state => state.dataListReducer.isEmptyError;
const mapDispatchToProps = (dispatch) => {

  return {}
}
export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ReactTableRow); 