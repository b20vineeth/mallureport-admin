import React, { Component } from 'react'
export default class Pagination extends Component {
    constructor(props) {
        super(props);

    }
    navigate(i) {
        console.log(i);
    }
    render() {
        let tableHtml = [];
        if (this.props.page && this.props.page.totalPage) {
            let page = this.props.page.totalPage;
            let i = 1;
            if (page > 1) {
                tableHtml.push(<li key={"PageL" + i}><a onClick={() => this.navigate({ i })}><span aria-hidden="true">&laquo;</span></a></li>)
                for (i = 1; i <= this.props.page.totalPage; i++) {
                    if (i == this.props.page.currentPage)
                        tableHtml.push(<li key={"Page" + i} className="active"><a onClick={() => this.navigate({ i })}>{i}</a></li>)
                    else
                        tableHtml.push(<li key={"Page" + i} ><a onClick={() => this.navigate({ i })}>{i}</a></li>)

                }
                let i = page;
                tableHtml.push(<li key={"PageLR" + i} ><a onClick={() => this.navigate({ i })}><span aria-hidden="true">&raquo;</span></a></li>)

            }
        }

        return (
            <React.Fragment>

                {tableHtml}
            </React.Fragment>
        )
    }
}
