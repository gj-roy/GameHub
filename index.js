import {AppRegistry, StyleSheet, Text, View} from 'react-native';

const HelloWorld = () => {
    return (
        <View style={styles.container}>
            <Text style={styles.hello}>{"Hello world\nMy name is Hojat\nAnd I live in Canada"}</Text>
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center'
    },
    hello: {
        fontSize: 20,
        textAlign: 'left',
        margin: 10,
        width: 'auto'
    },
});

AppRegistry.registerComponent(
    'ReactActivity',
    () => HelloWorld,
);
