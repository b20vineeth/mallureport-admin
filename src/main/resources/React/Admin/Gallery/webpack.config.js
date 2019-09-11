var webpack = require("webpack");
var path = require("path");
var HtmlWebpackPlugin = require("html-webpack-plugin");

module.exports = {
  context: __dirname,
  mode: "production",
  entry: {
    main: "./src/index.js"
  },
  devtool: "source-map",
  output: {
    path: path.resolve(__dirname, "dist"),
    filename: "[name].min.js"
  },
  resolve: {
    extensions: ['.jsx', '.js']
  },
  devServer: {
    port: 8081
},
  module: {
    rules: [
      {
        test: /\.(js|jsx)$/,
        exclude: /node_modules/,
        use: ["babel-loader"]
      },
      {
        test: /\.(css)$/,
        use: ["style-loader", "css-loader"]//order is important : css-loader passes the css to style-loader and it uses the o/p of cssloader and creates js function that run during page load ie. style-loader applies styles to header
      }, {
      test: /\.woff(\?v=\d+\.\d+\.\d+)?$/,
	  use: [ { loader: 'file-loader'  }]
    }, {
      test: /\.woff2(\?v=\d+\.\d+\.\d+)?$/,
      use: [ { loader: 'file-loader'  }]
    }, {
      test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/,
      use: [ { loader: 'file-loader'  }]
    }, {
      test: /\.eot(\?v=\d+\.\d+\.\d+)?$/,
      use: [ { loader: 'file-loader'  }]
    }, {
      test: /\.svg(\?v=\d+\.\d+\.\d+)?$/,
      use: [ { loader: 'file-loader'  }]
    },
	{
        test: /\.(jpe?g|png|gif)$/i,
        loaders: [
          'file-loader?hash=sha512&digest=hex&name=[hash].[ext]',
           
        ]
      } 
    ]
  },
  plugins: [new HtmlWebpackPlugin({
    filename: 'index.html',  // name of the file that is going to get created and placed in dist folder
    template: 'src/template.html'
  })]
};

