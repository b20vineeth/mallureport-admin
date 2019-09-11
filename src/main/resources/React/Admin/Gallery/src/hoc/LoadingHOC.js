import React from 'react'
import isEmpty from 'lodash/isEmpty'
import  './LoadingHOC.css';

export const LoadingHOC = (loadingProp) => (WrappedComponent) => {
    return class LoadingHOC extends React.Component {
        render() { 

          return this.props[loadingProp]==false ?
          <div className="loader" /> : <WrappedComponent {...this.props} />;
      }
    }
}

//this.props[`${property}`]   : this.props.contacts  , this.props['flights'] or this.props[property]
//This is a high order function 