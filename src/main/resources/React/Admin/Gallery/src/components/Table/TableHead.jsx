import React, { Component } from 'react'
export default class TableHead extends Component {
    constructor(props) {
        super(props);


    }
    render() {
        let header = this.props.Header;
         if (header.length > 0) {
            
            return (<th className={this.props.headerStyle}>{this.props.Header}</th>)
        }

        else {
            return (<th className={this.props.headerStyle}>&nbsp;</th>)
        }
    }
}
