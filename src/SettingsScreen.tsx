import {View, Text} from 'react-native'
import React from 'react'
import {SettingsSectionItem} from "./ui/SettingsSectionItem";
import {GameHubColors} from "./theme/GameHubTheme";

export const SettingsScreen = () => {
    return (
        <View style={{
            flex: 1,
            flexDirection: 'column',
            backgroundColor: 'white'
        }}>
            <View
                style={{
                    paddingVertical: 12,
                    paddingHorizontal: 14,

                }}
            >
                <Text style={{
                    color: GameHubColors.secondary,
                    fontWeight: '500',
                    letterSpacing: 0.6,
                }}>General</Text>
                <SettingsSectionItem title="Theme" description="Light"/>
                <SettingsSectionItem title="Language" description="English"/>
            </View>

            <View
                style={{
                    backgroundColor: GameHubColors.neutral,
                    height: 30,
                }}
            />
            <View style={{
                paddingTop: 12,
                paddingHorizontal: 14,
            }}>
                <Text style={{
                    color: GameHubColors.secondary,
                    fontWeight: '500',
                    letterSpacing: 0.6,
                    paddingTop: 8,
                }}>About</Text>
                <SettingsSectionItem title="Source Code" description="View GameHub's source code"/>
                <SettingsSectionItem title="Version" description="v1.3.0 release"/>
                <SettingsSectionItem title="Privacy Policy" description="View GameHub's privacy policies"/>

            </View>

        </View>

    )
}
