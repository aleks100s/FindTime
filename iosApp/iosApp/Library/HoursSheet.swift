import SwiftUI

struct HoursSheet: View {
	@Binding var hours: [Int]
	@Environment(\.presentationMode) var presentationMode
	
	var body: some View {
		NavigationView {
			VStack {
				List {
					ForEach(hours, id: \.self) { hour in
						Text("\(hour)")
					}
				}
			}
			.navigationTitle("Found Meeting Hours")
			.toolbar {
				ToolbarItem(placement: .navigationBarTrailing) {
					Button(action: {
						presentationMode.wrappedValue.dismiss()
					}) {
						Image(systemName: "xmark")
							.frame(alignment: .trailing)
							.foregroundColor(.black)
					}
				}
			}
		}
	}
}

struct HoursSheet_Previews: PreviewProvider {
	static var previews: some View {
		HoursSheet(hours: .constant([8, 9, 10]))
	}
}
