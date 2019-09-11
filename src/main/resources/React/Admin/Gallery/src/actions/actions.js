import { resolve } from "url";
import axios from 'axios' 
import * as types from './action-types'
//https://uinames.com/api/?amount=50&region=united states&ext
//'http://192.168.43.201/test.json'
const getContacts = () => {
    var config =
    {
        method: 'GET',
        mode: 'no-cors',
        headers: {
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS',
        },
        withCredentials: true,
        credentials: 'same-origin',
    };

    return axios(types.URL + '/gallery.list?data=1001', config)
        .then(response => response.data)

}

export const getRenderTable = (dispatch, getState) => {
    const state = getState();
    const data = state.dataListReducer.datasss;
    const datalength = state.dataListReducer.datasss.length;
    const dataHeader = state.dataListReducer.columns;

    let fieldRow = [];
     if(data.length>0)
        data.map(function (element, index) {
            let fieldList = [];

            dataHeader.map(function (headerElement, index) {
                let accessor = headerElement.accessor;
                let field = {
                    "accessor": element[accessor]?element[accessor]:"",
                    'style': headerElement["style"]?headerElement["style"]:"",
                    "tool": headerElement["editTool"],
                    "id":element["id"]
                };
                fieldList.push(field);
            });
            fieldRow.push(fieldList);

        }); 
     
    updateDataList(dispatch, fieldRow);


}
export const updateDataList = (dispatch, fieldRow) => {

    dispatch({ type: types.SAVE_FIELD_DATA, data: fieldRow });
}
export const getContactsFn = (dispatch) => {
    getContacts().then((content) => dispatch(getContactsAction(content)))
}

export const getContactsFfunction = (dispatch) => {
 

}

const getContactsAction = content => (
    {
        type: types.LIST_SUCCESS,
        gallery: content.galleryList?content.galleryList:"",
        page: content.page,
        isLoaded:true

    })

export const setFilterText = (filterText) => ({ type: types.SET_FILTERTEXT, data: filterText })

export const fetchContacts = (contacts) => ({ type: types.CONTACTS, data: contacts });

export const deleteAction = (index) => ({ type: types.DELETE_ROW, data: index });

export const updateAction = (data) => ({ type: types.EDIT_ROW, data });

