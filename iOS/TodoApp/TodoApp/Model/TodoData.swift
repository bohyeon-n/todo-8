//
//  TodoData.swift
//  TodoApp
//
//  Created by TTOzzi on 2020/04/08.
//  Copyright © 2020 TTOzzi. All rights reserved.
//

import Foundation

struct TodoData: Codable {
    var success: Bool
    var response: Category
}

struct Category: Codable {
    var category: [Column]
}

struct Column: Codable {
    var id: Int
    var title: String
    var cards: [Card]
}

struct Card: Codable, Equatable {
    var id: Int
    var categoryId: Int
    var title: String
    var author: String
    var contents: String?
    var createdDate: String
    var modifiedDate: String
}

struct Response: Codable {
    var success: Bool
    var response: Card
}
