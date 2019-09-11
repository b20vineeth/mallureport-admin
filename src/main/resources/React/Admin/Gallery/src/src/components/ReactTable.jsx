import React, { Component } from 'react'
import  ReactTableRow  from '../containers/ReactTableRowContainer'
export default class ReactTable extends Component {
    constructor(props) {
        super(props); 
         this.state=({
            tableHead: []
        });

    }
    componentDidMount() {
        this.props.dataLoad(this.props.dataList, this.props.columns);
      }
      
    render() {  
         
     
         
        return (


            <div className="row pad">

             <table className="table table-fixed">
                   
             <ReactTableRow/>
                      
                </table> 

            </div>

        )
    }
}
