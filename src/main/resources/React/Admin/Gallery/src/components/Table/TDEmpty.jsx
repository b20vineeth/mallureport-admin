import React, { Component } from 'react'
export default class TDEmpty extends Component {
    constructor(props) {
        super(props);
       
   }
    render() {
        let randomKey=Math.random();
       
        return (
             <tr className='tdcolspan'><td   key={'td1empt'+randomKey}  colSpan={this.props.column} className="col-xs-12">{this.props.message}</td></tr> 
           )
    }
}
