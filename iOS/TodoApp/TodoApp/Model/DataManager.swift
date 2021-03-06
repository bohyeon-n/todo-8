//
//  DataManager.swift
//  TodoApp
//
//  Created by TTOzzi on 2020/04/08.
//  Copyright © 2020 TTOzzi. All rights reserved.
//

import Foundation

class DataManager {
    static let dataDidLoad = NSNotification.Name.init("dataDidLoad")
    
    private var data: [Column]?
    
    func loadData() {
        let url = NetworkManager.serverUrl + "board"
        NetworkManager.httpRequest(url: url, method: .GET) { (data, _, error) in
            guard let data = data else { return }
            do {
                let decoder = JSONDecoder()
                let decodedData = try decoder.decode(TodoData.self, from: data)
                if self.data != decodedData.response.category {
                    self.data = decodedData.response.category
                    NotificationCenter.default.post(name: DataManager.dataDidLoad, object: nil)
                }
            } catch {
            
            }
        }
    }
    
    func data(of identifier: Int?) -> Column? {
        guard let data = data else { return nil }
        for column in data {
            if column.id == identifier {
                return column
            }
        }
        return nil
    }
}
