const { override, addWebpackAlias, getBabelLoader } = require('customize-cra')

const addReactHotLoader = config => {
	if (config.mode === 'production') return config
	getBabelLoader(config, false).options.plugins.push('react-hot-loader/babel')
	return config
}

module.exports = override(addWebpackAlias({ 'react-dom': '@hot-loader/react-dom' }), addReactHotLoader)
