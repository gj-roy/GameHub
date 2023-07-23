// Never convert this file to TSX.
import {AppRegistry, Text, View} from 'react-native';

const HelloWorld = () => {
    return (
        <View style={{
            flex: 1,
            justifyContent: 'center',
            alignItems: 'center'
        }}>
            <Text style={{
                fontSize: 20,
                textAlign: 'left',
                margin: 10,
                width: 'auto',
                color: 'darkblue'
            }}>{"Hello world\nMy name is Hojat\nAnd I live in Canada"}</Text>
        </View>
    );
};



AppRegistry.registerComponent(
    'ReactActivity',
    () => HelloWorld,
);
